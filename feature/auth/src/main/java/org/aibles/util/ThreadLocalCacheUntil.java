package org.aibles.util;

import java.util.Map;
import java.util.HashMap;

public class ThreadLocalCacheUntil {

  private static final ThreadLocal<Map<String, Object>> threadLocalCache = ThreadLocal.withInitial(HashMap::new);

  public static Object get(String key) {
    return threadLocalCache.get().get(key);
  }

  public static void put(String key, Object value) {
    threadLocalCache.get().put(key, value);
  }

  public static void clear() {
    threadLocalCache.get().clear();
  }
}
