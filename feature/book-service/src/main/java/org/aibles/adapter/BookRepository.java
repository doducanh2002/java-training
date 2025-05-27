package org.aibles.adapter;

import org.aibles.entity.Book;
import org.aibles.framework.controller.dto.update.UpdateBookRequest;

public interface BookRepository {
  Book create(Book book);

  Book getById(Long id);

  Book update(UpdateBookRequest request, Long id);

  void deleteById(Long id);
}
