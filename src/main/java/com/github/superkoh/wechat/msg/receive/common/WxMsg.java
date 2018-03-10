package com.github.superkoh.wechat.msg.receive.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "xml")
abstract public class WxMsg {

  /**
   * 开发者微信号
   */
  private String toUserName;
  /**
   * 发送方帐号（一个OpenID）
   */
  private String fromUserName;
  /**
   * 消息创建时间 （整型）
   */
  private Long createTime;
  /**
   * 消息类型
   */
  private String msgType;
  /**
   * 消息id，64位整型
   */
  private Long msgId;
}
