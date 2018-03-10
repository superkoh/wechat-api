package com.github.superkoh.wechat.msg.receive.normal;

import com.github.superkoh.wechat.msg.receive.common.WxMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxTextMsg extends WxMsg {

  /**
   * 文本消息内容
   */
  private String content;
}
