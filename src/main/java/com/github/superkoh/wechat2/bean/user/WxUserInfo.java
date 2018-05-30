package com.github.superkoh.wechat2.bean.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class WxUserInfo {

  private Integer subscribe;
  @JsonProperty("openid")
  private String openId;
  private String nickname;
  private Integer sex;
  private String language;
  private String city;
  private String province;
  private String country;
  @JsonProperty("headimgurl")
  private String headImgUrl;
  @JsonProperty("subscribe_time")
  private Long subscribeTime;
  @JsonProperty("unionid")
  private String unionId;
  private String remark;
  @JsonProperty("groupid")
  private Long groupId;
  @JsonProperty("tagid_list")
  private List<Integer> tagIdList;
  @JsonProperty("subscribe_scene")
  private String subscribeScene;
  @JsonProperty("qr_scene")
  private Long qrScene;
  @JsonProperty("qr_scene_str")
  private String qrSceneStr;

}
