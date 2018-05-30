package com.github.superkoh.wechat2.bean.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.superkoh.wechat2.bean.common.WxMiniProgram;
import java.util.HashMap;
import java.util.Map;
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
  private Map<String, InnerData> data = new HashMap<>();

  @Data
  private class InnerData {

    InnerData(String value, String color) {
      this.value = value;
      this.color = color;
    }

    private String value;
    private String color;
  }

  public void addData(String keyword, String value, String color) {
    data.put(keyword, new InnerData(value, color));
  }
}
