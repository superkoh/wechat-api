package com.github.superkoh.wechat.msg;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.superkoh.wechat.msg.receive.common.WxMsg;
import java.io.IOException;

public class WxMsgMapper {

  private static final XmlMapper xmlMapper;

  static {
    xmlMapper = new XmlMapper();
    xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    xmlMapper.setSerializationInclusion(Include.NON_NULL);
    xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
  }

  public static <T extends WxMsg> T fromXml(String msg, Class<T> clazz) throws IOException {
    return xmlMapper.readValue(msg, clazz);
  }

  public static String toXml(Object msg) throws IOException {
    return xmlMapper.writeValueAsString(msg);
  }
}
