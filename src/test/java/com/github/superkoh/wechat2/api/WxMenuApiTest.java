package com.github.superkoh.wechat2.api;

import com.github.superkoh.wechat.Constant;
import com.github.superkoh.wechat2.bean.menu.WxCombinedMenu;
import com.github.superkoh.wechat2.bean.BaseResult;
import com.github.superkoh.wechat2.bean.menu.WxButton;
import com.github.superkoh.wechat2.bean.menu.WxMenu;
import com.github.superkoh.wechat2.bean.token.WxAccessToken;
import com.github.superkoh.wechat2.exception.WxException;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class WxMenuApiTest {

  @Test
  void create() throws WxException {
    WxAccessToken accessToken = WxTokenApi.getToken(Constant.appId, Constant.secret);

    WxButton button1 = WxButton.viewButton("123", "https://www.baidu.com");
    WxButton button2 = WxButton.viewButton("456", "https://www.baidu.com");
    WxMenu menu = new WxMenu(Arrays.asList(button1, button2));
    BaseResult result = WxMenuApi.create(accessToken.getAccessToken(), menu);

    System.out.println(result);
  }

  @Test
  void get() throws WxException {
    WxAccessToken accessToken = WxTokenApi.getToken(Constant.appId, Constant.secret);

    WxCombinedMenu combinedMenu = WxMenuApi.get(accessToken.getAccessToken());

    System.out.println(combinedMenu);
  }
}