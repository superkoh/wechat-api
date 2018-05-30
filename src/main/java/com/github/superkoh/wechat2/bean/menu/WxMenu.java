package com.github.superkoh.wechat2.bean.menu;

import java.util.List;
import lombok.Data;

@Data
public class WxMenu extends WxMenuId {

  /**
   * 必填，一级菜单数组，个数应为1~3个
   */
  private List<WxButton> button;

  public WxMenu() {
  }

  public WxMenu(List<WxButton> button) {
    this.button = button;
  }
}
