package com.github.superkoh.wechat.msg.reply;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.github.superkoh.wechat.msg.reply.common.WxReply;
import com.github.superkoh.wechat.msg.reply.common.WxReplyBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 回复视频消息
 *
 * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140543">doc</a>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxVideoReply extends WxReply {

  public WxVideoReply() {
    setMsgType("video");
  }

  /**
   * 通过素材管理中的接口上传多媒体文件，得到的id。
   */
  @JacksonXmlCData
  private String mediaId;
  /**
   * 视频消息的标题
   */
  @JacksonXmlCData
  private String title;
  /**
   * 视频消息的描述
   */
  @JacksonXmlCData
  private String description;

  public static class Builder extends WxReplyBuilder<WxVideoReply, Builder> {

    @Override
    protected WxVideoReply newInstance() {
      return new WxVideoReply();
    }

    public Builder mediaId(String mediaId) {
      reply.setMediaId(mediaId);
      return this;
    }

    public Builder title(String title) {
      reply.setTitle(title);
      return this;
    }

    public Builder description(String description) {
      reply.setDescription(description);
      return this;
    }
  }
}
