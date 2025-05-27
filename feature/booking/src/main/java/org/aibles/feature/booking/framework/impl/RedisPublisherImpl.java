package org.aibles.feature.booking.framework.impl;

import org.aibles.feature.booking.adapter.repository.RedisPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
public class RedisPublisherImpl implements RedisPublisher {

  private final RedisTemplate<String, Object> redisTemplate;
  private final ChannelTopic channelTopic;

  public RedisPublisherImpl(RedisTemplate<String, Object> redisTemplate, ChannelTopic channelTopic) {
    this.redisTemplate = redisTemplate;
    this.channelTopic = channelTopic;
  }

  public void publishPromotionImportedEvent(String message) {
    redisTemplate.convertAndSend(channelTopic.getTopic(), message);
  }
}
