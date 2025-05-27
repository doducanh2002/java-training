package org.aibles.business.usecaseImpl;

import org.aibles.adapter.BookRepository;
import org.aibles.business.UpdateBookUseCase;
import org.aibles.entity.Book;
import org.aibles.framework.controller.dto.update.UpdateBookRequest;

public class UpdateBookUseCaseImpl implements UpdateBookUseCase {
  private final BookRepository bookRepository;

  public UpdateBookUseCaseImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Book update(UpdateBookRequest request, Long id) {
    return bookRepository.update(request, id);
  }
}
