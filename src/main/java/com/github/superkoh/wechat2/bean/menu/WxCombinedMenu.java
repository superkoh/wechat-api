package com.github.superkoh.wechat2.bean.menu;

import lombok.Data;

@Data
public class WxCombinedMenu {

  private WxMenu menu;
  private WxConditionalMenu conditionalMenu;
}
