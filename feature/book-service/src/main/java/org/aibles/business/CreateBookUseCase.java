package org.aibles.business;

import org.aibles.entity.Book;

public interface CreateBookUseCase {
  Book create(Book book);
}
