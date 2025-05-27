package org.aibles.feature.booking.adapter.repository;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface BookingCacheRepository {

  void save(String key, Object value, long timeToLive, TimeUnit timeUnit);

  void save(String key, String hashKey, Object value);

  <T> void add(String key, T value, Class<T> clazz);

  <T> void add(String key, Set<T> values, Class<T> clazz);

  Optional<Object> get(String key, String hashKey);

  Optional<Object> get(String key);

  <T> Set<T> members(String key, Class<T> clazz);

  void delete(String key, String hashKey);

  void delete(String key);

  <T> void remove(String key, T value);

  <T> void remove(String key, Set<T> values);

  <T> T getOrDefault(String key, T defaultValue);

  <T> T getOrDefault(String key, String hashKey, T defaultValue);
}
