package com.github.superkoh.wechat.msg.receive.normal;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.superkoh.wechat.msg.receive.common.WxMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxLocationMsg extends WxMsg {

  /**
   * 地理位置维度
   */
  @JacksonXmlProperty(localName = "Location_X")
  private Double locationX;
  /**
   * 地理位置经度
   */
  @JacksonXmlProperty(localName = "Location_Y")
  private Double locationY;
  /**
   * 地图缩放大小
   */
  private Integer scale;
  /**
   * 地理位置信息
   */
  private String label;
}
