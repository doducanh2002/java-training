package org.aibles.student.framework.controller.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentResponse {
  private Long id;
  private String name;
  private int age;
  private String hometown;

  @Override
  public String toString() {
    return "UpdateStudentResponse{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", hometown='" + hometown + '\'' +
        '}';
  }
}
