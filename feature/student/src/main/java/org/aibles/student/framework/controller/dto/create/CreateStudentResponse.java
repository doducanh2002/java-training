package org.aibles.student.framework.controller.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentResponse extends CreateStudentRequest {

  private Long id;

  @Override
  public String toString() {
    return "CreateStudentResponse{" +
        "id=" + id +
        ", name='" + getName() + '\'' +
        ", age=" + getAge() +
        ", hometown='" + getHometown() + '\'' +
        '}';
  }
}
