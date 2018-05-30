package com.github.superkoh.wechat2.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseResult {

  @JsonProperty("errcode")
  private Integer errCode;
  @JsonProperty("errmsg")
  private String errMsg;
}
