package org.aibles.student.framework.controller.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {
  private String name;
  private int age;
  private String hometown;

  @Override
  public String toString() {
    return "CreateStudentRequest{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", hometown='" + hometown + '\'' +
        '}';
  }
}
