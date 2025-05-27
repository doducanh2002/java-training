package org.aibles.business.usecaseImpl;

import org.aibles.adapter.BookRepository;
import org.aibles.business.GetBookByIdUseCase;
import org.aibles.entity.Book;


public class GetBookByIdUseCaseImpl implements GetBookByIdUseCase {
  private final BookRepository bookRepository;

  {
  }

  public GetBookByIdUseCaseImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }


  @Override
  public Book getById(Long id) {
    return bookRepository.getById(id);
  }
}
