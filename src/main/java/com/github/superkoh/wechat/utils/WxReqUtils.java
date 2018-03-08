package com.github.superkoh.wechat.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.superkoh.wechat.common.WxException;
import com.github.superkoh.wechat.sns.bean.WxSnsUserInfo;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.val;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
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

    Security.addProvider(new BouncyCastleProvider());
  }

  public static void get(Request.Builder request) throws WxException {
    try {
      Request req = request.get().build();
      logger.debug("GET: {}", req.url());
      Response response = httpClient.newCall(request.get().build()).execute();
      String resStr = Objects.requireNonNull(response.body()).string();
      logger.debug("RES: {}", resStr);
      JSONObject resObject = JSON.parseObject(resStr);
      if (resObject.containsKey("errcode") && !resObject.getInteger("errcode").equals(0)) {
        throw new WxException(resObject.getInteger("errcode"), resObject.getString("errmsg"));
      }
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
      throw new WxException(-1, e.getMessage());
    }
  }

  public static <T> T get(Request.Builder request, Class<T> clazz, boolean cacheable)
      throws WxException {
    try {
      Request req = request.get().build();
      logger.debug("GET: {}", req.url());
      Response response = httpClient.newCall(request.get().build()).execute();
      String resStr = Objects.requireNonNull(response.body()).string();
      logger.debug("RES: {}", resStr);
      JSONObject resObject = JSON.parseObject(resStr);
      if (resObject.containsKey("errcode") && !resObject.getInteger("errcode").equals(0)) {
        throw new WxException(resObject.getInteger("errcode"), resObject.getString("errmsg"));
      }
      return objectMapper.readValue(resStr, clazz);
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
      throw new WxException(-1, e.getMessage());
    }
  }

  public static <T> T get(Request.Builder request, Class<T> clazz) throws WxException {
    return get(request, clazz, false);
  }

  public static void post(Request.Builder request, Object body) throws WxException {
    try {
      String json = objectMapper.writeValueAsString(body);
      Request req = request
          .post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json))
          .build();
      logger.debug("POST: {}, DATA: {}", req.url(), json);
      Response response = httpClient.newCall(req).execute();
      String resStr = Objects.requireNonNull(response.body()).string();
      logger.debug("RES: {}", resStr);
      JSONObject resObject = JSON.parseObject(resStr);
      if (resObject.containsKey("errcode") && !resObject.getInteger("errcode").equals(0)) {
        throw new WxException(resObject.getInteger("errcode"), resObject.getString("errmsg"));
      }
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
      throw new WxException(-1, e.getMessage());
    }
  }

  public WxSnsUserInfo decryptUserInfo(String encryptedData, String iv, String sessionKey) {
    val res = decrypt(Base64.decodeBase64(encryptedData),
        Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
    if (null != res && res.length > 0) {
      try {
        val resStr = new String(res, "UTF8");
        return objectMapper.readValue(resStr, WxSnsUserInfo.class);
      } catch (IOException e) {
        logger.error(e.getMessage(), e);
        return null;
      }
    }
    return null;
  }

  private static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) {
    try {
      val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
      val sKeySpec = new SecretKeySpec(keyByte, "AES");
      val params = AlgorithmParameters.getInstance("AES");
      params.init(new IvParameterSpec(ivByte));
      cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);// 初始化
      return cipher.doFinal(content);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }
}
