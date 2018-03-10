package com.github.superkoh.wechat.api.template.domain;

import lombok.Data;
import lombok.Getter;

@Getter
public class WxTemplateData {

  private InnerData first;
  private InnerData keynote1;
  private InnerData keynote2;
  private InnerData keynote3;
  private InnerData remark;

  @Data
  private class InnerData {

    public InnerData(String value, String color) {
      this.value = value;
      this.color = color;
    }

    private String value;
    private String color;
  }

  public void setFirst(String value, String color) {
    this.first = new InnerData(value, color);
  }

  public void setKeynote1(String value, String color) {
    this.keynote1 = new InnerData(value, color);
  }

  public void setKeynote2(String value, String color) {
    this.keynote2 = new InnerData(value, color);
  }

  public void setKeynote3(String value, String color) {
    this.keynote3 = new InnerData(value, color);
  }

  public void setRemark(String value, String color) {
    this.remark = new InnerData(value, color);
  }
}
