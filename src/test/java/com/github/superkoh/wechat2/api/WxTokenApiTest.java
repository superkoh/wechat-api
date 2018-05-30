package com.github.superkoh.wechat2.api;

import static org.junit.jupiter.api.Assertions.*;

import com.github.superkoh.wechat.Constant;
import com.github.superkoh.wechat2.bean.token.WxAccessToken;
import com.github.superkoh.wechat2.exception.WxException;
import org.junit.jupiter.api.Test;

class WxTokenApiTest {

  @Test
  void getToken() throws WxException {
    WxAccessToken accessToken = WxTokenApi.getToken(Constant.appId, Constant.secret);
    System.out.println(accessToken);
  }
}