package com.github.superkoh.wechat.api.base;

import com.github.superkoh.wechat.api.WxAbstractApi;
import com.github.superkoh.wechat.api.base.domain.WxTicket;
import com.github.superkoh.wechat.common.WxException;
import com.github.superkoh.wechat.common.cache.Cache;
import com.github.superkoh.wechat.utils.WxCacheUtils;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class WxTicketApi extends WxAbstractApi {

  private static final HttpUrl TICKET_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin/ticket"));

  public WxTicketApi(String appId, String appSecret) {
    super(appId, appSecret);
  }

  /**
   * 获取微信ticket
   *
   * https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&amp;type=jsapi
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;id=mp1421141115">doc</a>
   */
  public WxTicket getTicket(String type) throws WxException {
    WxTicket ticket;
    String key = getAppId() + "_" + getAppSecret() + "_" + type + "_ticket";
    Cache cache = WxCacheUtils.getCache();
    if (null != cache) {
      ticket = (WxTicket) cache.get(key);
      if (null != ticket) {
        return ticket;
      }
    }
    ticket = get(
        new Request.Builder().url(
            TICKET_API.newBuilder()
                .addPathSegment("getticket")
                .addQueryParameter("access_token", getAccessToken())
                .addQueryParameter("type", type)
                .build()),
        WxTicket.class);
    if (null != cache) {
      cache.put(key, ticket);
    }
    return ticket;
  }

  /**
   * 获取微信jsapi ticket
   */
  public WxTicket getTicket() throws WxException {
    return getTicket("jsapi");
  }
}
