package com.github.superkoh.wechat2.api;

import com.github.superkoh.wechat2.bean.ticket.WxTicket;
import com.github.superkoh.wechat2.exception.WxException;
import com.github.superkoh.wechat2.utils.WxReqUtils;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class WxTicketApi {

  private static final HttpUrl TICKET_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin/ticket"));

  /**
   * 获取微信ticket
   *
   * https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&amp;type=jsapi
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;id=mp1421141115">doc</a>
   */
  public static WxTicket getTicket(String accessToken, String type) throws WxException {
    return WxReqUtils.get(
        new Request.Builder().url(
            TICKET_API.newBuilder()
                .addPathSegment("getticket")
                .addQueryParameter("access_token", accessToken)
                .addQueryParameter("type", type)
                .build()),
        WxTicket.class);
  }

  /**
   * 获取微信jsapi ticket
   */
  public WxTicket getJsapiTicket(String accessToken) throws WxException {
    return getTicket(accessToken, "jsapi");
  }
}
