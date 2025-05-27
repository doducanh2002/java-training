package org.aibles.student.business.usecaseimpl;

import org.aibles.student.adapter.repository.StudentRepository;
import org.aibles.student.business.CreateStudentUseCase;
import org.aibles.student.entity.Student;

public class CreateStudentUseCaseImpl implements CreateStudentUseCase {
  private final StudentRepository studentRepository;

  public CreateStudentUseCaseImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public Student create(Student student) {
    return studentRepository.create(student);
  }
}
