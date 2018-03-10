package com.github.superkoh.wechat.msg.receive.normal;

import com.github.superkoh.wechat.msg.receive.common.WxMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxLinkMsg extends WxMsg {

  /**
   * 消息标题
   */
  private String title;
  /**
   * 消息描述
   */
  private String description;
  /**
   * 消息链接
   */
  private String url;
}
