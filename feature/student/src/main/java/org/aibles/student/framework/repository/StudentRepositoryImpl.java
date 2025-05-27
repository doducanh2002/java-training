package org.aibles.student.framework.repository;

import lombok.extern.slf4j.Slf4j;
import org.aibles.business.exception.NotFoundException;
import org.aibles.student.adapter.repository.StudentRepository;
import org.aibles.student.entity.Student;
import org.aibles.student.framework.controller.dto.update.UpdateStudentRequest;
import org.aibles.student.framework.repository.datasource.StudentLocalDatasource;
import org.aibles.student.framework.repository.domain.StudentDatabaseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class StudentRepositoryImpl implements StudentRepository {
  private final StudentLocalDatasource studentLocalDatasource;

  public StudentRepositoryImpl(StudentLocalDatasource studentLocalDatasource) {
    this.studentLocalDatasource = studentLocalDatasource;
  }

  @Override
  public Student create(Student student) {
    log.info(" === Start api create new student === ");
    log.info(" === Request Body : {} === ", student);
    var studentDatabaseEntity = convertToStudentDatabaseEntity(student);
    studentDatabaseEntity = studentLocalDatasource.save(studentDatabaseEntity);
    if (studentDatabaseEntity.getName() == null) {
      throw new NotFoundException();
    }
    log.info(" === Finish api create new student, Student id :{} ", studentDatabaseEntity.getId());
    return convertToStudent(studentDatabaseEntity);
  }

  @Override
  public Student getById(Long id) {
    log.info(" === Start api GetById student === ");
    log.info(" === Long id : {} === ", id);
    Optional<StudentDatabaseEntity> optionalStudentDatabase = studentLocalDatasource.findById(id);
    if (!optionalStudentDatabase.isPresent()) {
      throw new NotFoundException();
    }
    var student = convertToStudent(optionalStudentDatabase.get());
    log.info(" === Finish api getById student, Student Id : {} ", student.getId());
    return student;
  }

  @Override
  public Student update(UpdateStudentRequest request, Long id) {
    log.info(" === Start api update student === ");
    log.info(" === Request Body : {}, Long id : {}  === ", request, id);
    Optional<StudentDatabaseEntity> optionalStudentDatabase = studentLocalDatasource.findById(id);
    if (!optionalStudentDatabase.isPresent()) {
      throw new NotFoundException();
    }
    StudentDatabaseEntity studentDatabase = optionalStudentDatabase.get();
    studentDatabase.setName(request.getName());
    studentDatabase.setAge(request.getAge());
    studentDatabase.setHometown(request.getHometown());
    studentDatabase = studentLocalDatasource.save(studentDatabase);
    log.info(" === Finish api update student : {} , Student Id ", studentDatabase.getId());
    return convertToStudent(studentDatabase);
  }

  @Override
  public void deleteById(Long id) {
    log.info(" === Start api update student === ");
    log.info(" === Long id : {} === ", id);
    Optional<StudentDatabaseEntity> optionalStudentDatabase = studentLocalDatasource.findById(id);
    if (!optionalStudentDatabase.isPresent()) {
      throw new NotFoundException();
    }
    log.info(" === Finish api update student, Student Id : {} ");
    studentLocalDatasource.deleteById(id);
  }

  @Override
  public List<Student> searchForStudents(String name) {
    log.info(" === Start api searchForStudents === ");
    log.info(" === Search name: {} === ", name);
    List<StudentDatabaseEntity> studentEntities = studentLocalDatasource.searchForStudents(name);
    if (studentEntities.isEmpty()) {
      log.info(" === Không tìm thấy học sinh nào có tên: {} === ", name);
      throw new NotFoundException();
    }
    List<Student> students = studentEntities.stream()
        .map(this::convertToStudent)
        .collect(Collectors.toList());
    log.info(" === Finish api searchForStudents, Found: {} students === ", students.size());
    return students;
  }

  private StudentDatabaseEntity convertToStudentDatabaseEntity(Student student) {
    StudentDatabaseEntity studentDatabaseEntity = new StudentDatabaseEntity();
    studentDatabaseEntity.setId(student.getId());
    studentDatabaseEntity.setName(student.getName());
    studentDatabaseEntity.setAge(student.getAge());
    studentDatabaseEntity.setHometown(student.getHometown());
    return studentDatabaseEntity;
  }

  private Student convertToStudent(StudentDatabaseEntity studentDatabaseEntity) {
    Student student = new Student();
    student.setId(studentDatabaseEntity.getId());
    student.setName(studentDatabaseEntity.getName());
    student.setAge(studentDatabaseEntity.getAge());
    student.setHometown(studentDatabaseEntity.getHometown());
    return student;
  }
}
