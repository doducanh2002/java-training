package org.aibles.framework.repository;

import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.BookRepository;
import org.aibles.business.exception.NotFoundException;
import org.aibles.entity.Book;
import org.aibles.framework.BookDatabaseEntity;
import org.aibles.framework.controller.dto.update.UpdateBookRequest;
import org.aibles.framework.repository.datasource.BookLocalDatasource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j

public class BookRepositoryImpl implements BookRepository {
  private final BookLocalDatasource bookLocalDatasource;

  public BookRepositoryImpl(BookLocalDatasource bookLocalDatasource) {
    this.bookLocalDatasource = bookLocalDatasource;
  }

  @Override
  public Book create(Book book) {
    var bookDatabaseEntity = convertToBookDatabaseEntity(book);
    bookDatabaseEntity = bookLocalDatasource.save(bookDatabaseEntity);
    if (bookDatabaseEntity.getAuthor() == null) {
      throw new NotFoundException();
    }
    return convertToBook(bookDatabaseEntity);
  }

  @Override
  public Book getById(Long id) {
    log.info(" === Start api getById book === ");
    log.info(" === Long Id :{} === ", id);
    Optional<BookDatabaseEntity> optionalBookDatabase = bookLocalDatasource.findById(id);
    if (!optionalBookDatabase.isPresent()) {
      throw new RuntimeException();
    }
    var book = convertToBook(optionalBookDatabase.get());
    log.info(" === Finish api getById book, Book Id : {} === ", book.getId());
    return book;
  }

  @Override
  public Book update(UpdateBookRequest request, Long id) {
    log.info(" === Start api update book === ");
    log.info(" === Request Body : {} , Long Id :{} === ", request, id);
    Optional<BookDatabaseEntity> optionalBookDatabase = bookLocalDatasource.findById(id);
    if (!optionalBookDatabase.isPresent()) {
      throw new NotFoundException();
    }
    BookDatabaseEntity bookDatabase = optionalBookDatabase.get();
    bookDatabase.setTitle(request.getTitle());
    bookDatabase.setAuthor(request.getAuthor());
    bookDatabase.setCategory(request.getCategory());
    bookDatabase.setPublishYear(request.getPublishYear());
    bookDatabase = bookLocalDatasource.save(bookDatabase);
    log.info(" === Finish api update book, Book Id : {} === ", bookDatabase.getId());
    return convertToBook(bookDatabase);
  }

  @Override
  public void deleteById(Long id) {
    log.info(" === Start api deleteByID book === ");
    log.info(" === Long Id :{} === ", id);
    Optional<BookDatabaseEntity> optionalBookDatabase = bookLocalDatasource.findById(id);
    if (!optionalBookDatabase.isPresent()) {
      throw new NotFoundException();
    }
    log.info(" === Finish api deleteId book, Book Id : {} === ");
    bookLocalDatasource.deleteById(id);
  }

  private BookDatabaseEntity convertToBookDatabaseEntity(Book book) {
    BookDatabaseEntity bookDatabaseEntity = new BookDatabaseEntity();
    bookDatabaseEntity.setId(book.getId());
    bookDatabaseEntity.setTitle(book.getTitle());
    bookDatabaseEntity.setAuthor(book.getAuthor());
    bookDatabaseEntity.setCategory(book.getCategory());
    bookDatabaseEntity.setPublishYear(book.getPublishYear());
    return bookDatabaseEntity;
  }

  private Book convertToBook(BookDatabaseEntity bookDatabaseEntity) {
    Book book = new Book();
    book.setId(bookDatabaseEntity.getId());
    book.setTitle(bookDatabaseEntity.getTitle());
    book.setAuthor(bookDatabaseEntity.getAuthor());
    book.setCategory(bookDatabaseEntity.getCategory());
    book.setPublishYear(bookDatabaseEntity.getPublishYear());
    return book;
  }

}
