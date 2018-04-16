package com.github.superkoh.wechat.api.sns;

import com.github.superkoh.wechat.api.WxAbstractApi;
import com.github.superkoh.wechat.api.sns.domain.WxSnsSession;
import com.github.superkoh.wechat.api.sns.domain.WxSnsUserInfo;
import com.github.superkoh.wechat.common.WxException;
import java.io.IOException;
import java.util.Objects;
import lombok.val;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.apache.commons.codec.binary.Base64;

public class WxSnsApi extends WxAbstractApi {

  private static final HttpUrl SNS_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/sns"));

  public WxSnsApi(String appId, String appSecret) {
    super(appId, appSecret);
  }

  public WxSnsSession jscode2session(String code)
      throws WxException {
    return get(
        new Request.Builder().url(
            SNS_API.newBuilder()
                .addPathSegment("jscode2session")
                .addQueryParameter("appid", getAppId())
                .addQueryParameter("secret", getAppSecret())
                .addQueryParameter("js_code", code)
                .addQueryParameter("grant_type", "authorization_code")
                .build()),
        WxSnsSession.class);
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

}
