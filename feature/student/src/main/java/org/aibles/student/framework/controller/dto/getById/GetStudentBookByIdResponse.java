package org.aibles.student.framework.controller.dto.getById;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentBookByIdResponse {
  private Long id;
  private String name;
  private int age;
  private String hometown;

  @Override
  public String toString() {
    return "GetStudentBookByIdResponse{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", hometown='" + hometown + '\'' +
        '}';
  }
}
