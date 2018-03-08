package com.github.superkoh.wechat.base.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WxAccessToken {

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("expires_in")
  private Long expiresIn;
}
