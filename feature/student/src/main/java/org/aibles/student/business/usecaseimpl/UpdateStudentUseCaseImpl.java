package org.aibles.student.business.usecaseimpl;

import org.aibles.student.adapter.repository.StudentRepository;
import org.aibles.student.business.UpdateStudentUseCase;
import org.aibles.student.entity.Student;
import org.aibles.student.framework.controller.dto.update.UpdateStudentRequest;

public class UpdateStudentUseCaseImpl implements UpdateStudentUseCase {
  private final StudentRepository studentRepository;

  public UpdateStudentUseCaseImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public Student update(UpdateStudentRequest request, Long id) {
    return studentRepository.update(request, id);
  }
}
