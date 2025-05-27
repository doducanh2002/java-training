package org.aibles.framework.controller.dto.getById;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GetBookByIdResponse {
  private Long id;
  private String title;
  private String author;
  private String category;
  private Integer publishYear;

  @Override
  public String toString() {
    return "GetBookByIdResponse{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", category='" + category + '\'' +
        ", publishYear=" + publishYear +
        '}';
  }
}
