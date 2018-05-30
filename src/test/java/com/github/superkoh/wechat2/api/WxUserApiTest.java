package com.github.superkoh.wechat2.api;

import static org.junit.jupiter.api.Assertions.*;

import com.github.superkoh.wechat.Constant;
import com.github.superkoh.wechat2.bean.token.WxAccessToken;
import com.github.superkoh.wechat2.bean.user.WxUserInfo;
import com.github.superkoh.wechat2.exception.WxException;
import org.junit.jupiter.api.Test;

class WxUserApiTest {

  @Test
  void info() throws WxException {
    WxAccessToken accessToken = WxTokenApi.getToken(Constant.appId, Constant.secret);

    WxUserInfo userInfo = WxUserApi
        .info(accessToken.getAccessToken(), "odRt2t2KlYAphgIEmcRoWA0rf-G4");

    System.out.println(userInfo);
  }

  @Test
  void infoBatchGet() {
  }
}