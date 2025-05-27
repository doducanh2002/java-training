package org.aibles.feature.booking.adapter.repository;

public interface RedisPublisher {
  void publishPromotionImportedEvent(String message);
}
