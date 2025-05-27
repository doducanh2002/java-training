package org.aibles.business.usecaseImpl;

import org.aibles.adapter.BookRepository;
import org.aibles.business.CreateBookUseCase;
import org.aibles.entity.Book;

public class CreateBookUseCaseImpl implements CreateBookUseCase {
  private final BookRepository bookRepository;

  public CreateBookUseCaseImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Book create(Book book) {
    return bookRepository.create(book);
  }
}
