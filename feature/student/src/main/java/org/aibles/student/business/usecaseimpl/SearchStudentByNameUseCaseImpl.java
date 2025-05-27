package org.aibles.student.business.usecaseimpl;

import org.aibles.student.adapter.repository.StudentRepository;
import org.aibles.student.business.SearchStudentByNameUseCase;
import org.aibles.student.entity.Student;

import java.util.List;

public class SearchStudentByNameUseCaseImpl implements SearchStudentByNameUseCase {
  private final StudentRepository studentRepository;

  public SearchStudentByNameUseCaseImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public List<Student> searchForStudents(String name) {
    return studentRepository.searchForStudents(name);
  }
}
