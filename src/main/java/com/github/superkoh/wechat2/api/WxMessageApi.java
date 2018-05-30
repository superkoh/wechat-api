package com.github.superkoh.wechat2.api;

import com.github.superkoh.wechat2.bean.message.WxTemplate;
import com.github.superkoh.wechat2.bean.message.WxTemplateSendResult;
import com.github.superkoh.wechat2.exception.WxException;
import com.github.superkoh.wechat2.utils.WxReqUtils;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class WxMessageApi {

  private static final HttpUrl TEMPLATE_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin/message/template"));
  private static final HttpUrl OPEN_TEMPLATE_API = Objects
      .requireNonNull(HttpUrl.parse("https://api.weixin.qq.com/cgi-bin/message/wxopen/template"));

  /**
   * 发送模板消息
   *
   * https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
   *
   * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&amp;=mp1433751277">doc</a>
   */
  public static WxTemplateSendResult templateSend(String accessToken, WxTemplate template)
      throws WxException {
    return WxReqUtils.post(
        new Request.Builder().url(TEMPLATE_API.newBuilder()
            .addPathSegment("send")
            .addQueryParameter("access_token", accessToken)
            .build()),
        template,
        WxTemplateSendResult.class);
  }

//  /**
//   * 发送小程序模板消息
//   *
//   * https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN
//   *
//   * @see <a href="https://mp.weixin.qq.com/debug/wxadoc/dev/api/notice.html#%E5%8F%91%E9%80%81%E6%A8%A1%E6%9D%BF%E6%B6%88%E6%81%AF">doc</a>
//   */
//  public static void wxOpenTemplateSend(WxOpenTemplate template) throws WxException {
//    post(
//        new Request.Builder().url(OPEN_TEMPLATE_API.newBuilder()
//            .addPathSegment("send")
//            .addQueryParameter("access_token", getAccessToken())
//            .build()),
//        template);
//  }
}
