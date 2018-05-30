package com.github.superkoh.wechat2.exception;

public class WxReqException extends RuntimeException {

  public WxReqException() {
  }

  public WxReqException(String message) {
    super(message);
  }

  public WxReqException(String message, Throwable cause) {
    super(message, cause);
  }

  public WxReqException(Throwable cause) {
    super(cause);
  }

  public WxReqException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
