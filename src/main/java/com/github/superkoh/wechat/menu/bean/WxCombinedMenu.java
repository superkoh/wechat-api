package com.github.superkoh.wechat.menu.bean;

import lombok.Data;

@Data
public class WxCombinedMenu {

  private WxMenu menu;
  private WxConditionalMenu conditionalMenu;
}
