package com.github.superkoh.wechat.msg.reply;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.superkoh.wechat.msg.reply.common.WxReply;
import com.github.superkoh.wechat.msg.reply.common.WxReplyBuilder;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 回复图文消息
 *
 * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543">doc</a>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WxNewsReply extends WxReply {

  public WxNewsReply() {
    setMsgType("news");
  }

  /**
   * 图文消息个数，限制为8条以内
   */
  private Integer articleCount;
  /**
   * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
   */
  @JacksonXmlElementWrapper(localName = "Articles")
  @JacksonXmlProperty(localName = "item")
  private List<Article> articles = new ArrayList<>();

  @Data
  @AllArgsConstructor
  public static class Article {

    /**
     * 图文消息标题
     */
    @JacksonXmlCData
    private String title;
    /**
     * 图文消息描述
     */
    @JacksonXmlCData
    private String description;
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    @JacksonXmlCData
    private String picUrl;
    /**
     * 点击图文消息跳转链接
     */
    @JacksonXmlCData
    private String url;
  }

  public static class Builder extends WxReplyBuilder<WxNewsReply, Builder> {

    @Override
    protected WxNewsReply newInstance() {
      return new WxNewsReply();
    }

    public Builder articleCount(Integer articleCount) {
      reply.setArticleCount(articleCount);
      return this;
    }

    public Builder addArticle(Article article) {
      reply.getArticles().add(article);
      return this;
    }
  }
}
