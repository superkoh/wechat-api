package com.github.superkoh.wechat.api.sns;

import com.github.superkoh.wechat.api.WxAbstractApi;
import com.github.superkoh.wechat.api.sns.domain.WxSnsOAuth2AccessToken;
import com.github.superkoh.wechat.api.sns.domain.WxSnsOAuthUserInfo;
import com.github.superkoh.wechat.api.sns.domain.WxSnsUserInfo;
import com.github.superkoh.wechat.common.WxException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.apache.commons.beanutils.BeanUtils;

public class WxSnsOAuthApi extends WxAbstractApi {

  private static final HttpUrl SNS_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/sns"));
  private static final HttpUrl SNS_OAUTH2_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/sns/oauth2"));

  public WxSnsOAuthApi(String appId, String appSecret) {
    super(appId, appSecret);
  }

  /**
   * 通过code换取网页授权access_token
   *
   * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&amp;secret=SECRET&amp;code=CODE&amp;grant_type=authorization_code
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140842">doc</a>
   */
  public WxSnsOAuth2AccessToken oAuth2AccessToken(String code) throws WxException {
    return get(
        new Request.Builder().url(SNS_OAUTH2_API.newBuilder()
            .addPathSegment("access_token")
            .addQueryParameter("appid", getAppId())
            .addQueryParameter("secret", getAppSecret())
            .addQueryParameter("code", code)
            .addQueryParameter("grant_type", "authorization_code")
            .build()), WxSnsOAuth2AccessToken.class);
  }

  /**
   * 刷新access_token（如果需要）
   *
   * https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&amp;grant_type=refresh_token&amp;refresh_token=REFRESH_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140842">doc</a>
   */
  public WxSnsOAuth2AccessToken oAuth2RefreshToken(String refreshToken) throws WxException {
    return get(
        new Request.Builder().url(SNS_OAUTH2_API.newBuilder()
            .addPathSegment("refresh_token")
            .addQueryParameter("appid", getAppId())
            .addQueryParameter("grant_type", "refresh_token")
            .addQueryParameter("refresh_token", refreshToken)
            .build()), WxSnsOAuth2AccessToken.class);
  }

  /**
   * 拉取用户信息(需scope为 snsapi_userinfo)
   *
   * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&amp;openid=OPENID&amp;lang=zh_CN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140842">doc</a>
   */
  public WxSnsUserInfo oAuth2UserInfo(String openId, String accessToken, String lang)
      throws WxException {
    WxSnsOAuthUserInfo oAuthUserInfo = get(
        new Request.Builder().url(SNS_API.newBuilder()
            .addPathSegment("userinfo")
            .addQueryParameter("access_token", accessToken)
            .addQueryParameter("openid", openId)
            .addQueryParameter("lang", lang)
            .build()), WxSnsOAuthUserInfo.class);
    WxSnsUserInfo userInfo = new WxSnsUserInfo();
    try {
      BeanUtils.copyProperties(userInfo, oAuthUserInfo);
    } catch (IllegalAccessException | InvocationTargetException ignored) {
    }
    return userInfo;
  }

  public WxSnsUserInfo oAuth2UserInfo(String openId, String accessToken)
      throws WxException {
    return oAuth2UserInfo(openId, accessToken, "zh_CN");
  }

  /**
   * 检验授权凭证（access_token）是否有效
   *
   * https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&amp;openid=OPENID
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140842">doc</a>
   */
  public boolean checkAccessToken(String openId, String accessToken) throws WxException {
    get(
        new Request.Builder().url(SNS_API.newBuilder()
            .addPathSegment("auth")
            .addQueryParameter("access_token", accessToken)
            .addQueryParameter("openid", openId)
            .build()));
    return true;
  }

}
