package com.github.superkoh.wechat2.bean.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class WxBatchUserInfo {

  @JsonProperty("user_info_list")
  private List<WxUserInfo> userInfoList;

}
