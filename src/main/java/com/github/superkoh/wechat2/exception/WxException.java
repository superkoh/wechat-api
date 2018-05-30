package com.github.superkoh.wechat2.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WxException extends Exception {

  private int errorCode;

  public WxException(int errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }
}
