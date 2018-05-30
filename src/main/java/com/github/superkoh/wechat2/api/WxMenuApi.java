package com.github.superkoh.wechat2.api;

import com.github.superkoh.wechat2.bean.BaseResult;
import com.github.superkoh.wechat2.bean.menu.WxCombinedMenu;
import com.github.superkoh.wechat2.bean.menu.WxConditionalMenu;
import com.github.superkoh.wechat2.bean.menu.WxMenu;
import com.github.superkoh.wechat2.bean.menu.WxMenuId;
import com.github.superkoh.wechat2.exception.WxException;
import com.github.superkoh.wechat2.utils.WxReqUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class WxMenuApi {

  private static final HttpUrl MENU_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin/menu"));

  /**
   * 自定义菜单创建接口
   *
   * https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421141013">doc</a>
   */
  public static BaseResult create(String accessToken, WxMenu menu) throws WxException {
    return WxReqUtils.post(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("create")
            .addQueryParameter("access_token", accessToken)
            .build()),
        menu,
        BaseResult.class);
  }

  /**
   * 自定义菜单查询接口
   *
   * https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421141014">doc</a>
   */
  public static WxCombinedMenu get(String accessToken) throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("get")
            .addQueryParameter("access_token", accessToken)
            .build()),
        WxCombinedMenu.class);
  }

  /**
   * 自定义菜单删除接口
   *
   * https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421141015">doc</a>
   */
  public static BaseResult delete(String accessToken) throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("delete")
            .addQueryParameter("access_token", accessToken)
            .build()),
        BaseResult.class);
  }

  /**
   * 创建个性化菜单
   *
   * https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1455782296">doc</a>
   */
  public static WxMenuId addConditional(String accessToken, WxConditionalMenu conditionalMenu)
      throws WxException {
    return WxReqUtils.post(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("addconditional")
            .addQueryParameter("access_token", accessToken)
            .build()),
        conditionalMenu,
        WxMenuId.class);
  }

  /**
   * 删除个性化菜单
   *
   * https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1455782296">doc</a>
   */
  public static BaseResult delConditional(String accessToken, WxMenuId menuId) throws WxException {
    return WxReqUtils.post(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("delconditional")
            .addQueryParameter("access_token", accessToken)
            .build()),
        menuId,
        BaseResult.class);
  }

  /**
   * 测试个性化菜单匹配结果
   *
   * https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1455782296">doc</a>
   */
  public static WxMenu tryMatch(String accessToken, String userId) throws WxException {
    Map<String, String> bodyMap = new HashMap<>();
    bodyMap.put("user_id", userId);
    return WxReqUtils.post(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("trymatch")
            .addQueryParameter("access_token", accessToken)
            .build()),
        bodyMap,
        WxMenu.class);
  }
}
