package com.github.superkoh.wechat2.bean.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WxaSnsSession {

  @JsonProperty("openid")
  private String openId;
  @JsonProperty("session_key")
  private String sessionKey;
}
