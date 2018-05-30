package com.github.superkoh.wechat2.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.superkoh.wechat2.bean.BaseResult;
import com.github.superkoh.wechat2.exception.WxException;
import com.github.superkoh.wechat2.exception.WxReqException;
import java.io.IOException;
import java.util.Objects;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxReqUtils {

  private static final Logger logger = LoggerFactory.getLogger(WxReqUtils.class);

  private static final OkHttpClient httpClient;
  private static final ObjectMapper objectMapper;

  static {
    httpClient = new OkHttpClient();

    objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.setSerializationInclusion(Include.NON_NULL);
  }

  public static <T> T get(Request.Builder request, Class<T> clazz)
      throws WxException {
    try {
      Request req = request.get().build();
      logger.debug("GET: {}", req.url());
      Response response = httpClient.newCall(request.get().build()).execute();
      String resStr = Objects.requireNonNull(response.body()).string();
      logger.debug("RES: {}", resStr);
      BaseResult result = objectMapper.readValue(resStr, BaseResult.class);
      if (null != result.getErrCode() && !result.getErrCode().equals(0)) {
        throw new WxException(result.getErrCode(), result.getErrMsg());
      }
      if (clazz.equals(BaseResult.class)) {
        return (T) result;
      }
      return objectMapper.readValue(resStr, clazz);
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
      throw new WxReqException("fail to get result from wechat", e);
    }
  }

  public static <T> T post(Request.Builder request, Object body,
      Class<T> clazz) throws WxException {
    try {
      String json = objectMapper.writeValueAsString(body);
      Request req = request
          .post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json))
          .build();
      logger.debug("POST: {}, DATA: {}", req.url(), json);
      Response response = httpClient.newCall(req).execute();
      String resStr = Objects.requireNonNull(response.body()).string();
      logger.debug("RES: {}", resStr);
      BaseResult result = objectMapper.readValue(resStr, BaseResult.class);
      if (null != result.getErrCode() && !result.getErrCode().equals(0)) {
        throw new WxException(result.getErrCode(), result.getErrMsg());
      }
      if (clazz.equals(BaseResult.class)) {
        return (T) result;
      }
      return objectMapper.readValue(resStr, clazz);
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
      throw new WxReqException("fail to get result from wechat", e);
    }
  }
}
