package com.github.superkoh.wechat.msg.reply;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.superkoh.wechat.msg.reply.common.WxReply;
import com.github.superkoh.wechat.msg.reply.common.WxReplyBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 回复音乐消息
 *
 * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1421140543">doc</a>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxMusicReply extends WxReply {

  public WxMusicReply() {
    setMsgType("music");
  }

  /**
   * 音乐标题
   */
  @JacksonXmlCData
  private String title;
  /**
   * 音乐描述
   */
  @JacksonXmlCData
  private String description;
  /**
   * 音乐链接
   */
  @JacksonXmlCData
  private String musicUrl;
  /**
   * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
   */
  @JacksonXmlCData
  @JacksonXmlProperty(localName = "HDMusicUrl")
  private String hqMusicUrl;
  /**
   * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
   */
  @JacksonXmlCData
  private String thumbMediaId;

  public static class Builder extends WxReplyBuilder<WxMusicReply, Builder> {

    @Override
    protected WxMusicReply newInstance() {
      return new WxMusicReply();
    }

    public Builder title(String title) {
      reply.setTitle(title);
      return this;
    }

    public Builder description(String description) {
      reply.setDescription(description);
      return this;
    }

    public Builder musicUrl(String musicUrl) {
      reply.setMusicUrl(musicUrl);
      return this;
    }

    public Builder hqMusicUrl(String hqMusicUrl) {
      reply.setHqMusicUrl(hqMusicUrl);
      return this;
    }

    public Builder thumbMediaId(String thumbMediaId) {
      reply.setThumbMediaId(thumbMediaId);
      return this;
    }
  }
}
