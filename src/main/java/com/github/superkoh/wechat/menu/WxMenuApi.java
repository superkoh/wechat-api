package com.github.superkoh.wechat.menu;

import com.github.superkoh.wechat.common.WxException;
import com.github.superkoh.wechat.menu.bean.WxCombinedMenu;
import com.github.superkoh.wechat.menu.bean.WxConditionalMenu;
import com.github.superkoh.wechat.menu.bean.WxMenu;
import com.github.superkoh.wechat.utils.WxReqUtils;
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
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013">doc</a>
   */
  public void create(String accessToken, WxMenu menu) throws WxException {
    WxReqUtils.post(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("create")
            .addQueryParameter("access_token", accessToken)
            .build()),
        menu);
  }

  /**
   * 自定义菜单查询接口
   *
   * https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014">doc</a>
   */
  public WxCombinedMenu get(String accessToken) throws WxException {
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
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141015">doc</a>
   */
  public void delete(String accessToken) throws WxException {
    WxReqUtils.get(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("delete")
            .addQueryParameter("access_token", accessToken)
            .build()));
  }

  /**
   * 创建个性化菜单
   *
   * https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296">doc</a>
   */
  public void addConditional(String accessToken, WxConditionalMenu conditionalMenu)
      throws WxException {
    WxReqUtils.post(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("addconditional")
            .addQueryParameter("access_token", accessToken)
            .build()),
        conditionalMenu);
  }

  /**
   * 删除个性化菜单
   *
   * https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296">doc</a>
   */
  public void delConditional(String accessToken) throws WxException {
    WxReqUtils.get(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("delconditional")
            .addQueryParameter("access_token", accessToken)
            .build()));
  }

  /**
   * 测试个性化菜单匹配结果
   *
   * https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296">doc</a>
   */
  public WxMenu tryMatch(String accessToken) throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("trymatch")
            .addQueryParameter("access_token", accessToken)
            .build()),
        WxMenu.class);
  }
}
