package com.github.superkoh.wechat.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class WxSignUtils {

  public static String jsapiSign(String nonceStr, String jsapiTicket, String timestamp,
      String url) {
    StringBuilder sb = new StringBuilder();
    sb.append("jsapi_ticket=").append(jsapiTicket).append("&");
    sb.append("noncestr=").append(nonceStr).append("&");
    sb.append("timestamp=").append(timestamp).append("&");
    sb.append("url=").append(url);
    return DigestUtils.sha1Hex(sb.toString());
  }
}
