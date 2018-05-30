package com.github.superkoh.wechat2.bean.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WxaSnsUserInfo {

  private String openId;
  @JsonProperty("nickName")
  private String nickname;
  private Integer gender;
  private String city;
  private String province;
  private String country;
  private String avatarUrl;
  private String unionId;
}
