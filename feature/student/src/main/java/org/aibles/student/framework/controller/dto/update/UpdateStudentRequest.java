package org.aibles.student.framework.controller.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentRequest {
  private String name;
  private int age;
  private String hometown;

  @Override
  public String toString() {
    return "UpdateStudentRequest{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", hometown='" + hometown + '\'' +
        '}';
  }
}
