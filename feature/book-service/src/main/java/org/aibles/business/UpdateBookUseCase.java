package org.aibles.business;

import org.aibles.entity.Book;
import org.aibles.framework.controller.dto.update.UpdateBookRequest;

public interface UpdateBookUseCase {
  Book update(UpdateBookRequest request, Long id);
}
