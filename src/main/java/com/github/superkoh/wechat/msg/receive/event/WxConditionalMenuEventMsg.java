package com.github.superkoh.wechat.msg.receive.event;

import com.github.superkoh.wechat.msg.receive.common.WxEventMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 自定义菜单事件
 *
 * 点击菜单拉取消息时的事件推送 CLICK, 点击菜单跳转链接时的事件推送 VIEW
 *
 * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140454">doc</a>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxConditionalMenuEventMsg extends WxEventMsg {

  /**
   * 事件KEY值
   *
   * CLICK: 与自定义菜单接口中KEY值对应
   *
   * VIEW: 设置的跳转URL
   */
  private String eventKey;
}
