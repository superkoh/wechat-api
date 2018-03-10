package com.github.superkoh.wechat.msg.receive.normal;

import com.github.superkoh.wechat.msg.receive.common.WxMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxVoiceMsg extends WxMsg {

  /**
   * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
   */
  private String mediaId;
  /**
   * 语音格式，如amr，speex等
   */
  private String format;
  /**
   * 语音识别结果，UTF8编码
   */
  private String recognition;
}
