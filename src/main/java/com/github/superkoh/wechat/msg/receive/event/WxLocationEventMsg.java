package com.github.superkoh.wechat.msg.receive.event;

import com.github.superkoh.wechat.msg.receive.common.WxEventMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 上报地理位置事件
 *
 * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140454">doc</a>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxLocationEventMsg extends WxEventMsg {

  /**
   * 地理位置纬度
   */
  private Double latitude;
  /**
   * 地理位置经度
   */
  private Double longitude;
  /**
   * 地理位置精度
   */
  private Double precision;
}
