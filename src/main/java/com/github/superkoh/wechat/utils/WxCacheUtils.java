package com.github.superkoh.wechat.utils;

import com.github.superkoh.wechat.common.cache.Cache;

public class WxCacheUtils {

  private static Cache cache;

  public static void setCache(Cache cache) {
    WxCacheUtils.cache = cache;
  }

  public static Cache getCache() {
    return cache;
  }
}
