package org.aibles.feature.booking.business.dto.response;

import org.aibles.feature.booking.entity.News;

public class NewsResponse {

  private String id;
  private String title;
  private Boolean status;
  private String createdBy;
  private Long createdAt;
  private String lastUpdatedBy;
  private Long lastUpdatedAt;

  public static NewsResponse from(News news) {
    NewsResponse response = new NewsResponse();
    response.setId(news.getId());
    response.setTitle(news.getTitle());
    response.setStatus(news.getStatus());
    return response;
  }

  public NewsResponse() {
  }

  public NewsResponse(String id, String title, Boolean status, String createdBy, Long createdAt,
      String lastUpdatedBy, Long lastUpdatedAt) {
    this.id = id;
    this.title = title;
    this.status = status;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
    this.lastUpdatedBy = lastUpdatedBy;
    this.lastUpdatedAt = lastUpdatedAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public String getLastUpdatedBy() {
    return lastUpdatedBy;
  }

  public void setLastUpdatedBy(String lastUpdatedBy) {
    this.lastUpdatedBy = lastUpdatedBy;
  }

  public Long getLastUpdatedAt() {
    return lastUpdatedAt;
  }

  public void setLastUpdatedAt(Long lastUpdatedAt) {
    this.lastUpdatedAt = lastUpdatedAt;
  }
}
