package org.aibles.feature.booking.framework.impl;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.aibles.feature.booking.adapter.repository.NewsRepository;
import org.aibles.feature.booking.business.dto.request.PagingRequest;
import org.aibles.feature.booking.business.dto.response.PagingResponse;
import org.aibles.feature.booking.entity.News;
import org.aibles.feature.booking.framework.datasource.NewsDatabaseEntity;
import org.aibles.feature.booking.framework.repository.NewsDatabaseEntityRepository;
import org.aibles.feature.booking.util.NewsMapping;
import org.aibles.feature.booking.util.SortUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NewsDatasourceImpl implements NewsRepository {

  private final NewsDatabaseEntityRepository newsDatabaseEntityRepository;

  @Override
  public PagingResponse<News> getNewsList(PagingRequest pagingReq, Boolean status) {
    boolean effectiveStatus = status == null || status;

    Pageable pageable = SortUtil.makePageable(
        pagingReq.getPage(),
        pagingReq.getSize(),
        pagingReq.getSort(),
          NewsDatabaseEntity.class);
    Page<NewsDatabaseEntity> newsPage = newsDatabaseEntityRepository.findAllByStatus(
        effectiveStatus, pageable);

    return NewsMapping.convertDatabaseToPagingEntity(newsPage);
  }

  private Set<String> createFieldList() {
    Set<String> validFields = new HashSet<>();
    Field[] fields = NewsDatabaseEntity.class.getDeclaredFields();
    for (Field field : fields) {
      validFields.add(field.getName());
    }
    return validFields;
  }
}
