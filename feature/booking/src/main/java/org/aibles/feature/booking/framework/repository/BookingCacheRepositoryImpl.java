package org.aibles.feature.booking.framework.repository;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.aibles.feature.booking.adapter.repository.BookingCacheRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookingCacheRepositoryImpl implements BookingCacheRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public BookingCacheRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(String key, Object value, long timeToLive, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeToLive, timeUnit);
    }

    @Override
    public void save(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public <T> void add(String key, T value, Class<T> clazz) {
        redisTemplate.opsForSet().add(key, clazz.cast(value));
    }

    @Override
    public <T> void add(String key, Set<T> values, Class<T> clazz) {
        for (T value : values) {
            add(key, value, clazz);
        }
    }

    @Override
    public Optional<Object> get(String key, String hashKey) {
        return Optional.ofNullable(redisTemplate.opsForHash().get(key, hashKey));
    }

    @Override
    public Optional<Object> get(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    @Override
    public <T> Set<T> members(String key, Class<T> clazz) {
        Set<Object> members = redisTemplate.opsForSet().members(key);

        if (members != null) {
            return members.stream()
                .map(clazz::cast)
                .collect(Collectors.toSet());
        }

        return Collections.emptySet();
    }

    @Override
    public void delete(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public <T> void remove(String key, T value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    @Override
    public <T> void remove(String key, Set<T> values) {
        redisTemplate.opsForSet().remove(key, values.toArray());
    }

    @Override
    public <T> T getOrDefault(String key, T defaultValue) {
        Object result = redisTemplate.opsForValue().get(key);
        return (result != null) ? (T) result : defaultValue;
    }

    @Override
    public <T> T getOrDefault(String key, String hashKey, T defaultValue) {
        Object result = redisTemplate.opsForHash().get(key, hashKey);
        return (result != null) ? (T) result : defaultValue;
    }
}





