package com.indonesia.bookstore.service;

import com.indonesia.bookstore.dto.AuthorRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {

    ResponseEntity<Object> retrieveAllAuthors();

    ResponseEntity<Object> retrieveAuthorById(Long id);

    ResponseEntity<Object> retrieveAuthorByName(String title);

    ResponseEntity<Object> addAuthor(AuthorRequestDto request);

    ResponseEntity<Object> updateAuthor(AuthorRequestDto request, Long id);

    ResponseEntity<Object> deleteAuthor(Long id);
}
