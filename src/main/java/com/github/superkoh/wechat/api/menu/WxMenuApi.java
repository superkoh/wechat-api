package com.github.superkoh.wechat.api.menu;

import com.github.superkoh.wechat.api.WxAbstractApi;
import com.github.superkoh.wechat.common.WxException;
import com.github.superkoh.wechat.api.menu.domain.WxCombinedMenu;
import com.github.superkoh.wechat.api.menu.domain.WxConditionalMenu;
import com.github.superkoh.wechat.api.menu.domain.WxMenu;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class WxMenuApi extends WxAbstractApi {

  private static final HttpUrl MENU_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin/menu"));

  public WxMenuApi(String appId, String appSecret) {
    super(appId, appSecret);
  }

  /**
   * 自定义菜单创建接口
   *
   * https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421141013">doc</a>
   */
  public void create(WxMenu menu) throws WxException {
    post(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("create")
            .addQueryParameter("access_token", getAccessToken())
            .build()),
        menu);
  }

  /**
   * 自定义菜单查询接口
   *
   * https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421141014">doc</a>
   */
  public WxCombinedMenu get() throws WxException {
    return get(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("get")
            .addQueryParameter("access_token", getAccessToken())
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
  public void delete() throws WxException {
    get(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("delete")
            .addQueryParameter("access_token", getAccessToken())
            .build()));
  }

  /**
   * 创建个性化菜单
   *
   * https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1455782296">doc</a>
   */
  public void addConditional(WxConditionalMenu conditionalMenu)
      throws WxException {
    post(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("addconditional")
            .addQueryParameter("access_token", getAccessToken())
            .build()),
        conditionalMenu);
  }

  /**
   * 删除个性化菜单
   *
   * https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1455782296">doc</a>
   */
  public void delConditional() throws WxException {
    get(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("delconditional")
            .addQueryParameter("access_token", getAccessToken())
            .build()));
  }

  /**
   * 测试个性化菜单匹配结果
   *
   * https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1455782296">doc</a>
   */
  public WxMenu tryMatch() throws WxException {
    return get(
        new Request.Builder().url(MENU_API.newBuilder()
            .addPathSegment("trymatch")
            .addQueryParameter("access_token", getAccessToken())
            .build()),
        WxMenu.class);
  }
}
