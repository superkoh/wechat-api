package com.github.superkoh.wechat.msg.reply;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.github.superkoh.wechat.msg.reply.common.WxReply;
import com.github.superkoh.wechat.msg.reply.common.WxReplyBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 回复图片消息
 *
 * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543">doc</a>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxImageReply extends WxReply {

  public WxImageReply() {
    setMsgType("image");
  }

  /**
   * 通过素材管理中的接口上传多媒体文件，得到的id。
   */
  @JacksonXmlCData
  private String mediaId;

  public static class Builder extends WxReplyBuilder<WxImageReply, Builder> {

    @Override
    protected WxImageReply newInstance() {
      return null;
    }

    public Builder mediaId(String mediaId) {
      reply.setMediaId(mediaId);
      return this;
    }
  }
}
