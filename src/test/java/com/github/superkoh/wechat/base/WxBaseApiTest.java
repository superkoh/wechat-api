package com.github.superkoh.wechat.base;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.superkoh.wechat.Constant;
import com.github.superkoh.wechat.common.WxException;
import lombok.val;
import org.junit.jupiter.api.Test;

class WxBaseApiTest {

  private WxBaseApi baseApi = new WxBaseApi();

  @Test
  void token() throws WxException {
    val token = baseApi.token(Constant.appId, Constant.secret);
    assertNotNull(token);
    System.out.println(token.getAccessToken());
  }

  @Test
  void cachedToken() {
  }

  @Test
  void getCallbackIp() throws WxException {
    val token = baseApi.cachedToken(Constant.appId, Constant.secret);
    val ipList = baseApi.getCallbackIp(token);
    assertNotNull(ipList);
    System.out.println(ipList);
  }
}