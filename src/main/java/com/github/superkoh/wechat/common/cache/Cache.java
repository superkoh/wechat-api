package com.github.superkoh.wechat.common.cache;

public interface Cache {

  void put(String key, Object value);

  Object get(String key);
}
