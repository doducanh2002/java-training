package org.aibles.student.business;

import org.aibles.student.entity.Student;

import java.util.List;

public interface SearchStudentByNameUseCase {
  List<Student> searchForStudents(String name);
}
