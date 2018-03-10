package com.github.superkoh.wechat.msg.reply;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.github.superkoh.wechat.msg.reply.common.WxReply;
import com.github.superkoh.wechat.msg.reply.common.WxReplyBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 回复文本消息
 *
 * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543">doc</a>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxTextReply extends WxReply {

  public WxTextReply() {
    setMsgType("text");
  }

  /**
   * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
   */
  @JacksonXmlCData
  private String content;

  public static class Builder extends WxReplyBuilder<WxTextReply, Builder> {

    @Override
    protected WxTextReply newInstance() {
      return new WxTextReply();
    }

    public Builder content(String content) {
      reply.setContent(content);
      return this;
    }
  }
}
