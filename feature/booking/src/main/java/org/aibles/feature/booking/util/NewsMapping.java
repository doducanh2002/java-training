package org.aibles.feature.booking.util;

import java.util.List;
import org.aibles.feature.booking.business.dto.response.PagingResponse;
import org.aibles.feature.booking.entity.News;
import org.aibles.feature.booking.framework.datasource.NewsDatabaseEntity;
import org.springframework.data.domain.Page;

public class NewsMapping {

  private NewsMapping() {
  }

  public static News convertDatabaseToEntity(NewsDatabaseEntity databaseEntity) {
    News news = new News();
    news.setId(databaseEntity.getId());
    news.setTitle(databaseEntity.getTitle());
    news.setStatus(databaseEntity.getStatus());
    return news;
  }

  public static PagingResponse<News> convertDatabaseToPagingEntity(
      Page<NewsDatabaseEntity> databaseEntityPage) {
    List<News> newsList = databaseEntityPage.getContent().stream()
        .map(NewsMapping::convertDatabaseToEntity).toList();
    return new PagingResponse<>(databaseEntityPage.getNumber(), databaseEntityPage.getSize(),
        databaseEntityPage.getTotalElements(), newsList);
  }
}
