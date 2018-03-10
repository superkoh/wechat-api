package com.github.superkoh.wechat.api.menu.domain;

import lombok.Data;

@Data
public class WxCombinedMenu {

  private WxMenu menu;
  private WxConditionalMenu conditionalMenu;
}
