package org.aibles.business.usecaseImpl;

import org.aibles.adapter.BookRepository;
import org.aibles.business.DeleteBookByIdUseCase;

public class DeleteBookByIdUseCaseImpl implements DeleteBookByIdUseCase {
  private final BookRepository bookRepository;

  public DeleteBookByIdUseCaseImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public void deleteById(Long id) {
    bookRepository.deleteById(id);
  }
}
