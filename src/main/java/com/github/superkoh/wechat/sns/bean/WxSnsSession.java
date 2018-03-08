package com.github.superkoh.wechat.sns.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WxSnsSession {

  @JsonProperty("openid")
  private String openId;
  @JsonProperty("session_key")
  private String sessionKey;
}
