package com.github.superkoh.wechat.api.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.superkoh.wechat.Constant;
import com.github.superkoh.wechat.api.menu.domain.WxButton;
import com.github.superkoh.wechat.api.menu.domain.WxCombinedMenu;
import com.github.superkoh.wechat.api.menu.domain.WxMenu;
import com.github.superkoh.wechat.common.WxException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class WxMenuApiTest {

  private WxMenuApi menuApi = new WxMenuApi(Constant.appId, Constant.secret);

  @Test
  void create() throws WxException {
    WxButton button = WxButton.viewButton("百度", "https://www.baidu.com");
    WxMenu menu = new WxMenu();
    List<WxButton> list = new ArrayList<>();
    list.add(button);
    menu.setButton(list);

    menuApi.create(menu);
  }

  @Test
  void get() throws WxException {
    WxCombinedMenu combinedMenu = menuApi.get();
    assertEquals("百度", combinedMenu.getMenu().getButton().get(0).getName());
    assertNotNull(combinedMenu);
  }

  @Test
  void delete() throws WxException {
    menuApi.delete();
  }

  @Test
  void addConditional() {
  }

  @Test
  void delConditional() {
  }

  @Test
  void tryMatch() {
  }
}