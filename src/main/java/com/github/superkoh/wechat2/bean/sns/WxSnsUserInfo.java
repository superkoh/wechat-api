package com.github.superkoh.wechat2.bean.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class WxSnsUserInfo {

  @JsonProperty("openid")
  private String openId;
  private String nickname;
  private Integer sex;
  private String city;
  private String province;
  private String country;
  @JsonProperty("headimgurl")
  private String headImgUrl;
  @JsonProperty("unionid")
  private String unionId;
  private List<String> privilege;

}
