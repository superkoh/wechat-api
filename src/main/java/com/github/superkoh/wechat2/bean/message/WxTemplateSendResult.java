package com.github.superkoh.wechat2.bean.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.superkoh.wechat2.bean.BaseResult;
import lombok.Data;

@Data
public class WxTemplateSendResult extends BaseResult {

  @JsonProperty("msgid")
  private Long msgId;
}
