package org.aibles.framework.controller.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequest {
  private String title;
  private String author;
  private String category;
  private Integer publishYear;

  @Override
  public String toString() {
    return "UpdateBookRequest{" +
        "title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", category='" + category + '\'' +
        ", publishYear=" + publishYear +
        '}';
  }
}
