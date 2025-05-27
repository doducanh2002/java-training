package org.aibles.framework.configuration;

import org.aibles.adapter.BookRepository;
import org.aibles.business.CreateBookUseCase;
import org.aibles.business.DeleteBookByIdUseCase;
import org.aibles.business.GetBookByIdUseCase;
import org.aibles.business.UpdateBookUseCase;
import org.aibles.business.usecaseImpl.CreateBookUseCaseImpl;
import org.aibles.business.usecaseImpl.DeleteBookByIdUseCaseImpl;
import org.aibles.business.usecaseImpl.GetBookByIdUseCaseImpl;
import org.aibles.business.usecaseImpl.UpdateBookUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.aibles.framework")

public class BeanConfiguration {
  private final BookRepository bookRepository;

  public BeanConfiguration(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Bean
  public CreateBookUseCase createBookUseCase() {
    return new CreateBookUseCaseImpl(bookRepository);
  }

  @Bean
  public GetBookByIdUseCase getBookByIdUseCase() {
    return new GetBookByIdUseCaseImpl(bookRepository);
  }

  @Bean
  public UpdateBookUseCase updateBookUseCase() {
    return new UpdateBookUseCaseImpl(bookRepository);
  }

  @Bean
  public DeleteBookByIdUseCase deleteBookByIdUseCase() {
    return new DeleteBookByIdUseCaseImpl(bookRepository);
  }
}


