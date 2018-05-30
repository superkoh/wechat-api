package com.github.superkoh.wechat2.api;

import com.github.superkoh.wechat2.bean.BaseResult;
import com.github.superkoh.wechat2.bean.sns.WxSnsOAuth2AccessToken;
import com.github.superkoh.wechat2.bean.sns.WxSnsUserInfo;
import com.github.superkoh.wechat2.bean.sns.WxaSnsSession;
import com.github.superkoh.wechat2.exception.WxException;
import com.github.superkoh.wechat2.utils.WxReqUtils;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class WxSnsApi {

  private static final HttpUrl SNS_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/sns"));

  /**
   * 通过code换取网页授权access_token
   *
   * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&amp;secret=SECRET&amp;code=CODE&amp;grant_type=authorization_code
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140842">doc</a>
   */
  public static WxSnsOAuth2AccessToken oAuth2AccessToken(String appId, String secret, String code)
      throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(SNS_API.newBuilder()
            .addPathSegment("oauth2")
            .addPathSegment("access_token")
            .addQueryParameter("appid", appId)
            .addQueryParameter("secret", secret)
            .addQueryParameter("code", code)
            .addQueryParameter("grant_type", "authorization_code")
            .build()),
        WxSnsOAuth2AccessToken.class);
  }

  /**
   * 刷新access_token（如果需要）
   *
   * https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&amp;grant_type=refresh_token&amp;refresh_token=REFRESH_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140842">doc</a>
   */
  public static WxSnsOAuth2AccessToken oAuth2RefreshToken(String appId, String refreshToken)
      throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(SNS_API.newBuilder()
            .addPathSegment("oauth2")
            .addPathSegment("refresh_token")
            .addQueryParameter("appid", appId)
            .addQueryParameter("grant_type", "refresh_token")
            .addQueryParameter("refresh_token", refreshToken)
            .build()),
        WxSnsOAuth2AccessToken.class);
  }

  /**
   * 拉取用户信息(需scope为 snsapi_userinfo)
   *
   * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&amp;openid=OPENID&amp;lang=zh_CN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140842">doc</a>
   */
  public static WxSnsUserInfo userInfo(String accessToken, String openId, String lang)
      throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(SNS_API.newBuilder()
            .addPathSegment("userinfo")
            .addQueryParameter("access_token", accessToken)
            .addQueryParameter("openid", openId)
            .addQueryParameter("lang", lang)
            .build()),
        WxSnsUserInfo.class);
  }

  public static WxSnsUserInfo userInfo(String accessToken, String openId)
      throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(SNS_API.newBuilder()
            .addPathSegment("userinfo")
            .addQueryParameter("access_token", accessToken)
            .addQueryParameter("openid", openId)
            .addQueryParameter("lang", "zh_CN")
            .build()),
        WxSnsUserInfo.class);
  }

  /**
   * 检验授权凭证（access_token）是否有效
   *
   * https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&amp;openid=OPENID
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140842">doc</a>
   */
  public static BaseResult auth(String accessToken, String openId) throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(SNS_API.newBuilder()
            .addPathSegment("auth")
            .addQueryParameter("access_token", accessToken)
            .addQueryParameter("openid", openId)
            .build()),
        BaseResult.class);
  }

  public static WxaSnsSession jscode2session(String appId, String appSecret, String code)
      throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(
            SNS_API.newBuilder()
                .addPathSegment("jscode2session")
                .addQueryParameter("appid", appId)
                .addQueryParameter("secret", appSecret)
                .addQueryParameter("js_code", code)
                .addQueryParameter("grant_type", "authorization_code")
                .build()),
        WxaSnsSession.class);
  }
}
