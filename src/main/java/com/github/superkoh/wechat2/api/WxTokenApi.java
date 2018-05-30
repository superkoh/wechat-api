package com.github.superkoh.wechat2.api;

import com.github.superkoh.wechat2.bean.token.WxAccessToken;
import com.github.superkoh.wechat2.exception.WxException;
import com.github.superkoh.wechat2.utils.WxReqUtils;
import java.util.Objects;
import lombok.Data;
import okhttp3.HttpUrl;
import okhttp3.Request;

@Data
public class WxTokenApi {

  /**
   * 获取微信 access_token
   */
  public static WxAccessToken getToken(String appId, String appSecret) throws WxException {
    return WxReqUtils.get(
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
}
