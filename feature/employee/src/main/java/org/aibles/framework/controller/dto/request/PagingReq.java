package org.aibles.framework.controller.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PagingReq {

  public static final int DEFAULT_PAGE_SIZE = 10;
  public static final int DEFAULT_PAGE_NUM = 1;
  @Min(1)
  private Integer pageSize = DEFAULT_PAGE_SIZE;

  @Min(1)
  private Integer pageNum = DEFAULT_PAGE_NUM;
}
