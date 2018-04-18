package com.github.superkoh.wechat.msg.receive.event;

import com.github.superkoh.wechat.msg.receive.common.WxEventMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 扫描带参数二维码事件
 *
 * 用户未关注时，进行关注后的事件推送 subscribe, 用户已关注时的事件推送 SCAN
 *
 * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140454">doc</a>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxQrCodeEventMsg extends WxEventMsg {

  /**
   * 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
   */
  private String eventKey;
  /**
   * 二维码的ticket，可用来换取二维码图片
   */
  private String ticket;
}
