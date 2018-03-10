package com.github.superkoh.wechat.api.menu.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WxConditionalMenu extends WxMenu {

  /**
   * 菜单匹配规则
   */
  @JsonProperty("matchrule")
  private WxMatchRule matchRule;
}
