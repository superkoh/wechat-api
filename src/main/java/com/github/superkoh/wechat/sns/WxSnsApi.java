package com.github.superkoh.wechat.sns;

import com.github.superkoh.wechat.common.WxException;
import com.github.superkoh.wechat.sns.bean.WxSnsSession;
import com.github.superkoh.wechat.utils.WxReqUtils;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class WxSnsApi {

  private static final HttpUrl SNS_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/sns"));

  public WxSnsSession jscode2session(String appId, String secret, String code)
      throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(
            SNS_API.newBuilder()
                .addPathSegment("jscode2session")
                .addQueryParameter("appid", appId)
                .addQueryParameter("secret", secret)
                .addQueryParameter("js_code", code)
                .addQueryParameter("grant_type", "authorization_code")
                .build()),
        WxSnsSession.class);
  }


}
