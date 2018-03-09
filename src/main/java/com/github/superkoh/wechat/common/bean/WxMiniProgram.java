package com.github.superkoh.wechat.common.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WxMiniProgram {

  @JsonProperty("appid")
  private String appId;
  @JsonProperty("pagepath")
  private String pagePath;
}
