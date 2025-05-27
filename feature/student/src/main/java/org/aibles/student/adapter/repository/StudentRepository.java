package org.aibles.student.adapter.repository;

import org.aibles.student.entity.Student;
import org.aibles.student.framework.controller.dto.update.UpdateStudentRequest;

import java.util.List;

public interface StudentRepository {
  Student create(Student student);

  Student getById(Long id);

  Student update(UpdateStudentRequest request, Long id);

  void deleteById(Long id);

  List<Student> searchForStudents(String name);
}
