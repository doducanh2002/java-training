package org.aibles.student.business.usecaseimpl;

import org.aibles.student.adapter.repository.StudentRepository;
import org.aibles.student.business.DeleteStudentByIdUseCase;

public class DeleteStudentByIdUseCaseImpl implements DeleteStudentByIdUseCase {
  private final StudentRepository studentRepository;

  public DeleteStudentByIdUseCaseImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public void deleteById(Long id) {
    studentRepository.deleteById(id);
  }
}
