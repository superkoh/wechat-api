package com.github.superkoh.wechat.msg.reply;

import com.github.superkoh.wechat.msg.reply.WxNewsReply.Article;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class WxNewsReplyTest {

  @Test
  void testReply() throws IOException {
    String reply = new WxNewsReply.Builder()
        .toUserName("123")
        .fromUserName("456")
        .articleCount(2)
        .addArticle(new Article("abc", "abc", "abc", "abc"))
        .addArticle(new Article("abc", "abc", "abc", "abc"))
        .build().toXml();
    System.out.println(reply);
  }
}