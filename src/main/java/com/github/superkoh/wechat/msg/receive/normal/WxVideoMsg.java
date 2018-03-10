package com.github.superkoh.wechat.msg.receive.normal;

import com.github.superkoh.wechat.msg.receive.common.WxMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxVideoMsg extends WxMsg {

  /**
   * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
   */
  private String mediaId;
  /**
   * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
   */
  private String thumbMediaId;
}
