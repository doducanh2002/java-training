package org.aibles.feature.booking.framework.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.MapHelper;
import org.aibles.feature.booking.business.job.PromotionGiveAwayJobDetail;
import org.aibles.feature.booking.entity.Promotion;
import org.aibles.quartz.service.QuartzService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisSubscriber implements MessageListener {

  private final QuartzService quartzService;
  private final MapHelper mapHelper;
  @Value("${quartz.group.promotion:promotion_giveaway_group}")
  private String promotionQuartzGroup;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    String channel = new String(pattern);
    String body = new String(message.getBody());
    log.info("Received message on channel '{}': {}", channel, body);
    if ("GIVE-AWAY-TOPIC".equalsIgnoreCase(channel)) {
      List<Promotion> promotions = null;
      try {
        promotions = mapHelper.mapListPromotionToList(body);
        for (Promotion p : promotions) {
          try {
            quartzService.createTimeJob(promotionQuartzGroup, p.getId(),
                PromotionGiveAwayJobDetail.class, p.getGiveAwayDay().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
          } catch (Exception e) {
            log.error(e.getMessage());
          }
        }
      } catch (JsonProcessingException e) {
        log.error(e.getMessage());
      }
    }
  }
}

