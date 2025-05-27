package org.aibles.student.framework.configuration;

import org.aibles.student.adapter.repository.StudentRepository;
import org.aibles.student.business.*;
import org.aibles.student.business.usecaseimpl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.aibles.framework")
public class StudentBeanConfiguration {
  private final StudentRepository studentRepository;


  public StudentBeanConfiguration(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Bean
  public CreateStudentUseCase createStudentUseCase() {
    return new CreateStudentUseCaseImpl(studentRepository);
  }

  @Bean
  public GetStudentByIdUseCase getStudentByIdUseCase() {
    return new GetStudentByIdUseCaseImpl(studentRepository);
  }

  @Bean
  public UpdateStudentUseCase updateStudentUseCase() {
    return new UpdateStudentUseCaseImpl(studentRepository);
  }

  @Bean
  public DeleteStudentByIdUseCase deleteStudentByIdUseCase() {
    return new DeleteStudentByIdUseCaseImpl(studentRepository);
  }

  @Bean
  public SearchStudentByNameUseCase searchStudentByNameUseCase() {
    return new SearchStudentByNameUseCaseImpl(studentRepository);
  }
}
