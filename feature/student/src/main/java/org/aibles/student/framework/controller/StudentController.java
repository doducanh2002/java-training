package org.aibles.student.framework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.student.business.*;
import org.aibles.student.entity.Student;
import org.aibles.student.framework.controller.dto.create.CreateStudentRequest;
import org.aibles.student.framework.controller.dto.create.CreateStudentResponse;
import org.aibles.student.framework.controller.dto.getById.GetStudentBookByIdResponse;
import org.aibles.student.framework.controller.dto.update.UpdateStudentRequest;
import org.aibles.student.framework.controller.dto.update.UpdateStudentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@Slf4j
@RequiredArgsConstructor
public class StudentController {
  private final CreateStudentUseCase createStudentUseCase;
  private final GetStudentByIdUseCase getStudentByIdUseCase;
  private final UpdateStudentUseCase updateStudentUseCase;
  private final DeleteStudentByIdUseCase deleteStudentByIdUseCase;
  private final SearchStudentByNameUseCase searchStudentByNameUseCase;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public CreateStudentResponse create(@RequestBody CreateStudentRequest request) {
    log.info(" === Start api create new student === ");
    log.info(" === Request Body : {} === ", request);
    Student student = createStudentUseCase.create(convertToBook(request));
    log.info(" === Finish api create new student, Student Id : {} === ");
    return convertToCreateStudentResponse(student);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public GetStudentBookByIdResponse getById(@PathVariable("id") Long id) {
    log.info(" === Start api getById student === ");
    log.info(" === Long id : {} === ", id);
    Student student = getStudentByIdUseCase.getById(id);
    log.info(" === Finish api getById student, Student Id : {} === ", student.getId());
    return convertToGetStudentByIdResponse(student);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public UpdateStudentResponse update(@RequestBody UpdateStudentRequest request, @PathVariable("id") Long id) {
    log.info(" === Start api update student === ");
    log.info(" === Request Body : {} ,Long id : {} === ", request, id);
    Student student = updateStudentUseCase.update(request, id);
    log.info(" === Finish api update student, Student Id : {} === ", student.getId());
    return convertToUpdateStudentResponse(student);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteById(@PathVariable("id") Long id) {
    log.info(" === Start api update student === ");
    log.info(" === Long id : {} === ", id);
    log.info(" === Finish api update student, Student Id : {} ");
    deleteStudentByIdUseCase.deleteById(id);
  }

  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public List<Student> searchByName(@RequestParam("name") String name) {
    log.info(" === Start api searchByName === ");
    log.info(" === Search name : {} === ", name);
    List<Student> student = searchStudentByNameUseCase.searchForStudents(name);
    log.info(" === Finish api searchByName, Found: {} students === ");
    return student;
  }

  private CreateStudentResponse convertToCreateStudentResponse(Student student) {
    CreateStudentResponse response = new CreateStudentResponse();
    response.setId(student.getId());
    response.setName(student.getName());
    response.setAge(student.getAge());
    response.setHometown(student.getHometown());
    return response;
  }

  private Student convertToBook(CreateStudentRequest request) {
    Student student = new Student();
    student.setName(request.getName());
    student.setAge(request.getAge());
    student.setHometown(request.getHometown());
    return student;
  }

  private GetStudentBookByIdResponse convertToGetStudentByIdResponse(Student student) {
    GetStudentBookByIdResponse response = new GetStudentBookByIdResponse();
    response.setId(student.getId());
    response.setName(student.getName());
    response.setAge(student.getAge());
    response.setHometown(student.getHometown());
    return response;
  }

  private UpdateStudentResponse convertToUpdateStudentResponse(Student student) {
    UpdateStudentResponse response = new UpdateStudentResponse();
    response.setId(student.getId());
    response.setName(student.getName());
    response.setAge(student.getAge());
    response.setHometown(student.getHometown());
    return response;
  }
}
