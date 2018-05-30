package com.github.superkoh.wechat2.api;

import com.github.superkoh.wechat2.bean.user.WxBatchUserInfo;
import com.github.superkoh.wechat2.bean.user.WxUserInfo;
import com.github.superkoh.wechat2.exception.WxException;
import com.github.superkoh.wechat2.utils.WxReqUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class WxUserApi {

  private static final HttpUrl USER_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin/user"));

  /**
   * 获取用户基本信息（包括UnionID机制
   *
   * https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&amp;openid=OPENID&amp;lang=zh_CN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;id=mp1421140839">doc</a>
   */
  public static WxUserInfo info(String accessToken, String openId, String lang) throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(USER_API.newBuilder()
            .addPathSegment("info")
            .addQueryParameter("access_token", accessToken)
            .addQueryParameter("openid", openId)
            .addQueryParameter("lang", lang)
            .build()),
        WxUserInfo.class);
  }

  public static WxUserInfo info(String accessToken, String openId) throws WxException {
    return info(accessToken, openId, "zh_CN");
  }

  /**
   * 获取用户基本信息（包括UnionID机制
   *
   * https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&amp;openid=OPENID&amp;lang=zh_CN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;id=mp1421140839">doc</a>
   */
  public static WxBatchUserInfo infoBatchGet(String accessToken, List<String> openIdList,
      String lang)
      throws WxException {
    List<Map<String, String>> ids = openIdList.stream().map(openId -> {
      Map<String, String> id = new HashMap<>();
      id.put("openid", openId);
      id.put("lang", lang);
      return id;
    }).collect(Collectors.toList());
    Map<String, List<Map<String, String>>> body = new HashMap<>();
    body.put("user_list", ids);
    return WxReqUtils.post(
        new Request.Builder().url(USER_API.newBuilder()
            .addPathSegment("info")
            .addPathSegment("batchget")
            .addQueryParameter("access_token", accessToken)
            .build()),
        body,
        WxBatchUserInfo.class);
  }
}
