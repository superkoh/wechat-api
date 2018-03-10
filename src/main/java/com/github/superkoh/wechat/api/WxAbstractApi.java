package com.github.superkoh.wechat.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.superkoh.wechat.common.WxException;
import com.github.superkoh.wechat.common.bean.WxAccessToken;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.Data;
import lombok.val;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
abstract public class WxAbstractApi {

  protected static final Logger logger = LoggerFactory.getLogger("WxApi");

  protected static final OkHttpClient httpClient;
  protected static final ObjectMapper objectMapper;

  protected static final Map<String, String> tokenCache = new ConcurrentHashMap<>();
  protected static final Map<String, Instant> tokenExpireCache = new ConcurrentHashMap<>();

  static {
    httpClient = new OkHttpClient();

    objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.setSerializationInclusion(Include.NON_NULL);

    Security.addProvider(new BouncyCastleProvider());
  }

  private String appId;
  private String appSecret;

  public WxAbstractApi(String appId, String appSecret) {
    this.appId = appId;
    this.appSecret = appSecret;
  }

  protected WxAccessToken getWxAccessToken() throws WxException {
    return get(
        new Request.Builder().url(
            Objects.requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin"))
                .newBuilder()
                .addPathSegment("token")
                .addQueryParameter("grant_type", "client_credential")
                .addQueryParameter("appid", appId)
                .addQueryParameter("secret", appSecret)
                .build()),
        WxAccessToken.class);
  }

  protected String getAccessToken() throws WxException {
    String key = appId + "_" + appSecret;
    String keyForExpire = key + "_expire";
    if (tokenExpireCache.containsKey(keyForExpire) && tokenCache.containsKey(key)) {
      Instant expireTime = tokenExpireCache
          .getOrDefault(keyForExpire, Instant.now().minusSeconds(1800));
      if (expireTime.isAfter(Instant.now())) {
        return tokenCache.get(key);
      }
    }
    WxAccessToken token = getWxAccessToken();
    tokenCache.put(key, token.getAccessToken());
    tokenExpireCache.put(keyForExpire, Instant.now().plusSeconds(token.getExpiresIn() / 2));
    return token.getAccessToken();
  }

  protected static void get(Request.Builder request) throws WxException {
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

  protected static <T> T get(Request.Builder request, Class<T> clazz, boolean cacheable)
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

  protected static <T> T get(Request.Builder request, Class<T> clazz) throws WxException {
    return get(request, clazz, false);
  }

  protected static void post(Request.Builder request, Object body) throws WxException {
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

  protected static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) {
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
