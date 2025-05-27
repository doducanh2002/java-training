package org.aibles.business;


import org.aibles.entity.Book;

public interface GetBookByIdUseCase {
  Book getById(Long id);
}
