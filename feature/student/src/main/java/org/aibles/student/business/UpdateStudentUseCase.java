package org.aibles.student.business;

import org.aibles.student.entity.Student;
import org.aibles.student.framework.controller.dto.update.UpdateStudentRequest;

public interface UpdateStudentUseCase {
  Student update(UpdateStudentRequest request, Long id);
}
