package org.aibles.framework.repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.aibles.adapter.repository.RedisCacheRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisCacheRepositoryImpl implements RedisCacheRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisCacheRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
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
    public Optional<Object> get(String key, String hashKey) {
        return Optional.ofNullable(redisTemplate.opsForHash().get(key, hashKey));
    }

    @Override
    public Optional<Object> get(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
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





