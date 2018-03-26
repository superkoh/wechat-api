package com.github.superkoh.wechat.api.template;

import com.github.superkoh.wechat.api.WxAbstractApi;
import com.github.superkoh.wechat.api.template.domain.WxOpenTemplate;
import com.github.superkoh.wechat.common.WxException;
import com.github.superkoh.wechat.api.template.domain.WxTemplate;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class WxTemplateApi extends WxAbstractApi {

  private static final HttpUrl TEMPLATE_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin/template"));
  private static final HttpUrl OPEN_TEMPLATE_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin/message/wxopen/template"));

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

  /**
   * 发送小程序模板消息
   *
   * https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/debug/wxadoc/dev/api/notice.html#%E5%8F%91%E9%80%81%E6%A8%A1%E6%9D%BF%E6%B6%88%E6%81%AF">doc</a>
   */
  public void openSend(WxOpenTemplate template) throws WxException {
    post(
        new Request.Builder().url(OPEN_TEMPLATE_API.newBuilder()
            .addPathSegment("send")
            .addQueryParameter("access_token", getAccessToken())
            .build()),
        template);
  }
}
