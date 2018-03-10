package com.github.superkoh.wechat.api.template;

import com.github.superkoh.wechat.api.WxAbstractApi;
import com.github.superkoh.wechat.common.WxException;
import com.github.superkoh.wechat.api.template.domain.WxTemplate;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class WxTemplateApi extends WxAbstractApi {

  private static final HttpUrl TEMPLATE_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin/template"));

  public WxTemplateApi(String appId, String appSecret) {
    super(appId, appSecret);
  }

  /**
   * 发送模板消息
   *
   * https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277">doc</a>
   */
  public void send(WxTemplate template)
      throws WxException {
    post(
        new Request.Builder().url(TEMPLATE_API.newBuilder()
            .addPathSegment("send")
            .addQueryParameter("access_token", getAccessToken())
            .build()),
        template);
  }
}
