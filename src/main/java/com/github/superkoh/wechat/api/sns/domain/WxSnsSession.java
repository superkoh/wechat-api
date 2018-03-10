package com.github.superkoh.wechat.api.sns.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WxSnsSession {

  @JsonProperty("openid")
  private String openId;
  @JsonProperty("session_key")
  private String sessionKey;
}
