package com.github.superkoh.wechat.base;

import com.github.superkoh.wechat.base.bean.WxIpList;
import com.github.superkoh.wechat.base.bean.WxAccessToken;
import com.github.superkoh.wechat.common.WxException;
import com.github.superkoh.wechat.utils.WxReqUtils;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class WxBaseApi {

  private static final HttpUrl BASE_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin"));

  private static final Map<String, String> tokenCache = new ConcurrentHashMap<>();
  private static final Map<String, Instant> tokenExpireCache = new ConcurrentHashMap<>();

  /**
   * 获取access_token
   *
   * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
   *
   * @See <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183">doc</a>
   */
  public WxAccessToken token(String appId, String secret) throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(
            BASE_API.newBuilder()
                .addPathSegment("token")
                .addQueryParameter("grant_type", "client_credential")
                .addQueryParameter("appid", appId)
                .addQueryParameter("secret", secret)
                .build()),
        WxAccessToken.class);
  }

  /**
   * 获取缓存的access_token
   */
  public String cachedToken(String appId, String secret) throws WxException {
    String key = appId + "_" + secret;
    String keyForExpire = key + "_expire";
    if (tokenExpireCache.containsKey(keyForExpire) && tokenCache.containsKey(key)) {
      Instant expireTime = tokenExpireCache
          .getOrDefault(keyForExpire, Instant.now().minusSeconds(1800));
      if (expireTime.isAfter(Instant.now())) {
        return tokenCache.get(key);
      }
    }
    WxAccessToken tokenRes = token(appId, secret);
    tokenCache.put(key, tokenRes.getAccessToken());
    tokenExpireCache.put(keyForExpire, Instant.now().plusSeconds(tokenRes.getExpiresIn() / 2));
    return tokenRes.getAccessToken();
  }

  /**
   * 获取微信服务器IP地址
   *
   * https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN
   *
   * @See <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140187">doc</a>
   */
  public WxIpList getCallbackIp(String accessToken) throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(
            BASE_API.newBuilder()
                .addPathSegment("getcallbackip")
                .addQueryParameter("access_token", accessToken)
                .build()),
        WxIpList.class);
  }
}
