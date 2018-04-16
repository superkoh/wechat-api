package com.github.superkoh.wechat.api.sns.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WxSnsOAuthUserInfo {

  @JsonProperty("openid")
  private String openId;
  private String nickname;
  @JsonProperty("sex")
  private Integer gender;
  private String city;
  private String province;
  private String country;
  @JsonProperty("headimgurl")
  private String avatarUrl;
  @JsonProperty("unionid")
  private String unionId;

}
