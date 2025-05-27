package org.aibles.student.business;

import org.aibles.student.entity.Student;

public interface GetStudentByIdUseCase {
  Student getById(Long id);
}
