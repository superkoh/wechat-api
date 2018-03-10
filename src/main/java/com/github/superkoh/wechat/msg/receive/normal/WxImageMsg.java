package com.github.superkoh.wechat.msg.receive.normal;

import com.github.superkoh.wechat.msg.receive.common.WxMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxImageMsg extends WxMsg {

  /**
   * 图片链接（由系统生成）
   */
  private String picUrl;
  /**
   * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
   */
  private String mediaId;
}
