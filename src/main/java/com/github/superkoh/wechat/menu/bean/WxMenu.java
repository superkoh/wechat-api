package com.github.superkoh.wechat.menu.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class WxMenu {

  /**
   * 必填，一级菜单数组，个数应为1~3个
   */
  private List<WxButton> button;

  @JsonProperty("menuid")
  private Long menuId;
}
