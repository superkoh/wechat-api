package com.github.superkoh.wechat.sns.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WxSnsUserInfo {

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
