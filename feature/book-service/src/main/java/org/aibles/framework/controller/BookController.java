package org.aibles.framework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.business.CreateBookUseCase;
import org.aibles.business.DeleteBookByIdUseCase;
import org.aibles.business.GetBookByIdUseCase;
import org.aibles.business.UpdateBookUseCase;
import org.aibles.entity.Book;
import org.aibles.framework.controller.dto.create.CreateBookRequest;
import org.aibles.framework.controller.dto.create.CreateBookResponse;
import org.aibles.framework.controller.dto.getById.GetBookByIdResponse;
import org.aibles.framework.controller.dto.update.UpdateBookRequest;
import org.aibles.framework.controller.dto.update.UpdateBookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@Slf4j
@RequiredArgsConstructor

public class BookController {
  private final CreateBookUseCase createBookUseCase;
  private final GetBookByIdUseCase getBookByIdUseCase;
  private final UpdateBookUseCase updateBookUseCase;
  private final DeleteBookByIdUseCase deleteBookByIdUseCase;

  @PostMapping()
  public CreateBookResponse create(@RequestBody CreateBookRequest request) {
    Book book = createBookUseCase.create(convertToBook(request));
    return convertToCreateBookResponse(book);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public GetBookByIdResponse getById(@PathVariable("id") Long id) {
    log.info(" === Start api getById book === ");
    log.info(" === Long id : {} === ", id);
    Book book = getBookByIdUseCase.getById(id);
    log.info(" === Finish api getById book, Book Id : {} === ", book.getId());
    return convertToGetBookByIdResponse(book);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public UpdateBookResponse update(@RequestBody UpdateBookRequest request, @PathVariable("id") Long id) {
    log.info(" === Start api update book === ");
    log.info(" === Request Body : {}, Long id : {} === ", request, id);
    Book book = updateBookUseCase.update(request, id);
    log.info(" === Finish api update book, Book Id : {} === ", book.getId());
    return convertToUpdateResponse(book);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteById(@PathVariable("id") Long id) {
    log.info(" === Start api deleteById book === ");
    log.info(" === Long id : {} === ", id);
    log.info(" === Finish api deleteByID book, Book Id : {} === ");
    deleteBookByIdUseCase.deleteById(id);

  }


  private CreateBookResponse convertToCreateBookResponse(Book book) {
    CreateBookResponse response = new CreateBookResponse();
    response.setTitle(book.getTitle());
    response.setAuthor(book.getAuthor());
    return response;
  }


  private Book convertToBook(CreateBookRequest request) {
    Book book = new Book();
    book.setTitle(request.getTitle());
    book.setAuthor(request.getAuthor());
    return book;
  }

  private GetBookByIdResponse convertToGetBookByIdResponse(Book book) {
    GetBookByIdResponse response = new GetBookByIdResponse();
    response.setId(book.getId());
    response.setAuthor(book.getAuthor());
    response.setTitle(book.getTitle());
    response.setPublishYear(book.getPublishYear());
    response.setCategory(book.getCategory());
    return response;
  }

  private UpdateBookResponse convertToUpdateResponse(Book book) {
    UpdateBookResponse response = new UpdateBookResponse();
    response.setId(book.getId());
    response.setAuthor(book.getAuthor());
    response.setTitle(book.getTitle());
    response.setPublishYear(book.getPublishYear());
    response.setCategory(book.getCategory());
    return response;
  }
}
