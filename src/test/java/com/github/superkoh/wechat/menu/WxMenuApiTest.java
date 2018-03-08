package com.github.superkoh.wechat.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.superkoh.wechat.Constant;
import com.github.superkoh.wechat.base.WxBaseApi;
import com.github.superkoh.wechat.common.WxException;
import com.github.superkoh.wechat.menu.bean.WxButton;
import com.github.superkoh.wechat.menu.bean.WxCombinedMenu;
import com.github.superkoh.wechat.menu.bean.WxMenu;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class WxMenuApiTest {

  private WxBaseApi baseApi = new WxBaseApi();
  private WxMenuApi menuApi = new WxMenuApi();

  @Test
  void create() throws WxException {
    String token = baseApi.cachedToken(Constant.appId, Constant.secret);
    assertNotNull(token);
    WxButton button = WxButton.viewButton("百度", "https://www.baidu.com");
    WxMenu menu = new WxMenu();
    List<WxButton> list = new ArrayList<>();
    list.add(button);
    menu.setButton(list);

    menuApi.create(token, menu);
  }

  @Test
  void get() throws WxException {
    String token = baseApi.cachedToken(Constant.appId, Constant.secret);
    WxCombinedMenu combinedMenu = menuApi.get(token);
    assertEquals("百度", combinedMenu.getMenu().getButton().get(0).getName());
    assertNotNull(combinedMenu);
  }

  @Test
  void delete() throws WxException {
    String token = baseApi.cachedToken(Constant.appId, Constant.secret);
    menuApi.delete(token);
  }
}