package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PagingReq {

  public static final int DEFAULT_PAGE_SIZE = 10;
  public static final int DEFAULT_PAGE_NUM = 1;
  @Min(1)
  private Integer page = DEFAULT_PAGE_SIZE;

  @Min(1)
  private Integer num = DEFAULT_PAGE_NUM;
}
