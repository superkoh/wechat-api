# wechat-api

微信 Java sdk

### 已完成接口

#### 公众号：基础接口

获取access_token

    WxBaseApi baseApi = new WxBaseApi();
    WxAccessToken accessToken = baseApi.token(appId, secret);

获取缓存的access_token

    WxBaseApi baseApi = new WxBaseApi();
    String token = baseApi.cachedToken(appId, secret);

#### 公众号：自定义菜单接口

自定义菜单创建接口

    WxMenuApi menuApi = new WxMenuApi();
    WxButton button = WxButton.viewButton("百度", "https://www.baidu.com");
    WxMenu menu = new WxMenu();
    List<WxButton> list = new ArrayList<>();
    list.add(button);
    menu.setButton(list);
    menuApi.create(accessToken, menu);
    
自定义菜单查询接口

自定义菜单删除接口

创建个性化菜单

删除个性化菜单

测试个性化菜单匹配结果
