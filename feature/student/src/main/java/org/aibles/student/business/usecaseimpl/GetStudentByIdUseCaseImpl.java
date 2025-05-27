package org.aibles.student.business.usecaseimpl;

import org.aibles.student.adapter.repository.StudentRepository;
import org.aibles.student.business.GetStudentByIdUseCase;
import org.aibles.student.entity.Student;

public class GetStudentByIdUseCaseImpl implements GetStudentByIdUseCase {

  private final StudentRepository studentRepository;

  public GetStudentByIdUseCaseImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public Student getById(Long id) {
    return studentRepository.getById(id);
  }
}
