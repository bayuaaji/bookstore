package com.indonesia.bookstore.service;

import com.indonesia.bookstore.dto.BookRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    ResponseEntity<Object> retrieveAllBooks();

    ResponseEntity<Object> retrieveBookById(Long id);

    ResponseEntity<Object> addBook(BookRequestDto request);

    ResponseEntity<Object> updateBook(BookRequestDto request, Long id);

    ResponseEntity<Object> deleteBook(Long id);

    ResponseEntity<Object> retrieveBookByTitle(String title);
    ResponseEntity<Object> retrieveBookByPriceMax(Float price);
}
