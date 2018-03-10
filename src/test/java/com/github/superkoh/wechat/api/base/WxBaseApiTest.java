package com.github.superkoh.wechat.api.base;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.superkoh.wechat.Constant;
import com.github.superkoh.wechat.common.bean.WxAccessToken;
import com.github.superkoh.wechat.api.base.domain.WxIpList;
import com.github.superkoh.wechat.common.WxException;
import org.junit.jupiter.api.Test;

class WxBaseApiTest {

  private WxBaseApi baseApi = new WxBaseApi(Constant.appId, Constant.secret);

  @Test
  void token() throws WxException {
    WxAccessToken token = baseApi.token();
    assertNotNull(token);
    System.out.println(token.getAccessToken());
  }

  @Test
  void cachedToken() {
  }

  @Test
  void getCallbackIp() throws WxException {
    WxIpList ipList = baseApi.getCallbackIp();
    assertNotNull(ipList);
    System.out.println(ipList);
  }
}