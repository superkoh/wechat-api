package com.github.superkoh.wechat.msg;

import com.github.superkoh.wechat.msg.receive.common.WxMsg;
import java.io.IOException;

public class WxMsgParser {

  public static <T extends WxMsg> T parse(String msg, Class<T> clazz) throws IOException {
    return WxMsgMapper.fromXml(msg, clazz);
  }
}
