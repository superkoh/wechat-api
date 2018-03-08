# wechat-api

微信 Java sdk

### 已完成接口

#### 公众号：基础接口

获取access_token

    WxBaseApi baseApi = new WxBaseApi();
    WxAccessToken accessToken = baseApi.token(appId, secret);

获取缓存的access_token

#### 公众号：自定义菜单接口

自定义菜单创建接口

自定义菜单查询接口

自定义菜单删除接口

创建个性化菜单

删除个性化菜单

测试个性化菜单匹配结果
