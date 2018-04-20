package com.github.superkoh.wechat.api.base.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WxTicket {

  private String ticket;
  @JsonProperty("expires_in")
  private Long expiresIn;
}
