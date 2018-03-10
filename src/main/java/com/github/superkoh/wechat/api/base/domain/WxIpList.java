package com.github.superkoh.wechat.api.base.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class WxIpList {

  @JsonProperty("ip_list")
  private List<String> ipList;
}
