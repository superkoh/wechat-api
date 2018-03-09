package com.github.superkoh.wechat.template.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.superkoh.wechat.common.bean.WxMiniProgram;
import lombok.Data;

@Data
public class WxTemplate {

  @JsonProperty("touser")
  private String toUser;
  @JsonProperty("template_id")
  private String templateId;
  private String url;
  @JsonProperty("miniprogram")
  private WxMiniProgram miniProgram;
  private WxTemplateData data;
}
