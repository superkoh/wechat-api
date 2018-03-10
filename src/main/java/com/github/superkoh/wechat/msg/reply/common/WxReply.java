package com.github.superkoh.wechat.msg.reply.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.github.superkoh.wechat.msg.WxMsgMapper;
import java.io.IOException;
import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JacksonXmlRootElement(localName = "xml")
abstract public class WxReply {

  /**
   * 接收方帐号（收到的OpenID）
   */
  @JacksonXmlCData
  private String toUserName;
  /**
   * 开发者微信号
   */
  @JacksonXmlCData
  private String fromUserName;
  /**
   * 消息创建时间 （整型）
   */
  private Long createTime = Instant.now().getEpochSecond();
  /**
   * 消息类型
   */
  @JacksonXmlCData
  private String msgType;

  public String toXml() throws IOException {
    return WxMsgMapper.toXml(this);
  }
}
