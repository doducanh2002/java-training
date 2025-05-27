package org.aibles.feature.booking.business.dto.response;

import java.util.List;

public class PagingResponse<T> {
  private int num;
  private int size;
  private long total;
  private List<T> pageData;

  public PagingResponse() {
  }

  public PagingResponse(int num, int size, long total, List<T> pageData) {
    this.num = num;
    this.size = size;
    this.total = total;
    this.pageData = pageData;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public List<T> getPageData() {
    return pageData;
  }

  public void setPageData(List<T> pageData) {
    this.pageData = pageData;
  }
}
