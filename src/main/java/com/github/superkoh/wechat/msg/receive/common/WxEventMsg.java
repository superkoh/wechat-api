package com.github.superkoh.wechat.msg.receive.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
abstract public class WxEventMsg extends WxMsg {

  /**
   * 事件类型
   */
  private String event;
}
