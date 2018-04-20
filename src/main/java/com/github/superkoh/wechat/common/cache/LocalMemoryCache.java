package com.github.superkoh.wechat.common.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;

public class LocalMemoryCache implements Cache {

  private long expireInSeconds = 4800;
  private com.github.benmanes.caffeine.cache.Cache<String, Object> objCache;

  public LocalMemoryCache() {
    objCache = Caffeine.newBuilder()
        .maximumSize(100)
        .expireAfterWrite(expireInSeconds, TimeUnit.SECONDS)
        .build();
  }

  public LocalMemoryCache(long expireInSeconds) {
    this.expireInSeconds = expireInSeconds;
    objCache = Caffeine.newBuilder()
        .maximumSize(100)
        .expireAfterWrite(expireInSeconds, TimeUnit.SECONDS)
        .build();
  }

  @Override
  public void put(String key, Object value) {
    objCache.put(key, value);
  }

  @Override
  public Object get(String key) {
    return objCache.getIfPresent(key);
  }

}
