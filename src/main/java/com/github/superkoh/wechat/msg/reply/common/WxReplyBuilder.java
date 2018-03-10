package com.github.superkoh.wechat.msg.reply.common;

abstract public class WxReplyBuilder<R extends WxReply, B extends WxReplyBuilder> {

  protected R reply;

  public WxReplyBuilder() {
    reply = newInstance();
  }

  abstract protected R newInstance();

  public B toUserName(String toUserName) {
    reply.setToUserName(toUserName);
    return (B) this;
  }

  public B fromUserName(String fromUserName) {
    reply.setFromUserName(fromUserName);
    return (B) this;
  }

  public R build() {
    return reply;
  }
}
