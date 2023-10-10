package com.indonesia.bookstore.service.impl;

import com.indonesia.bookstore.dto.AuthorRequestDto;
import com.indonesia.bookstore.dto.AuthorResponseDto;
import com.indonesia.bookstore.dto.GeneralResponse;
import com.indonesia.bookstore.handler.CustomException;
import com.indonesia.bookstore.model.Author;
import com.indonesia.bookstore.model.Book;
import com.indonesia.bookstore.repository.AuthorRepository;
import com.indonesia.bookstore.repository.BookRepository;
import com.indonesia.bookstore.service.AuthorService;
import com.indonesia.bookstore.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public ResponseEntity<Object> retrieveAllAuthors() {

        try {
            List<Author> authors = authorRepository.findAll();

            if(authors == null){
                throw new CustomException("Author is empty!");
            }

            List<AuthorResponseDto> authorResponseDtoList = DtoMapper.authorListMapper(authors);

            return GeneralResponse.generateResponse("successfully retrieved all authors", HttpStatus.OK, authorResponseDtoList);
        } catch (CustomException e){
            return GeneralResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);

        }
    }

    @Override
    public ResponseEntity<Object> retrieveAuthorById(Long id) {

        try {
            Optional<Author> authorById = authorRepository.findById(id);
            if(authorById.isEmpty()){
                throw new CustomException("Author not found with id: " + id);
            }

            AuthorResponseDto result = DtoMapper.authorMapper(authorById.get());

            return GeneralResponse.generateResponse("successfully retrieved author with id: " + id, HttpStatus.OK, result);
        } catch (CustomException e){
            return GeneralResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseEntity<Object> addAuthor(AuthorRequestDto request) {

        try {
            List<Book> books = new ArrayList<>();
            if(!books.isEmpty()){
                for(Long bookId : request.getBooks()){
                    Optional<Book> book = bookRepository.findById(bookId);
                    books.add(book.get());
                }
            }

            Author author = new Author();

            author.setName(request.getName());
            author.setBooks(books);
            author.setImage(request.getImage());
            author.setCreatedAt(new Date());
            author.setUpdatedAt(null);

            Author savedAuthor = authorRepository.save(author);
            if(savedAuthor == null){
                throw new CustomException("Internal Server Error");
            }

            AuthorResponseDto result = DtoMapper.authorMapper(savedAuthor);

            return GeneralResponse.generateResponse("successfully added author", HttpStatus.CREATED, result);
        } catch (CustomException e){
            return GeneralResponse.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);

        }
    }

    @Override
    public ResponseEntity<Object> updateAuthor(AuthorRequestDto request, Long id) {

        try {
            Author author = authorRepository.findById(id).orElseThrow(() -> new CustomException("author not found with id: " + id));
            author.setName(request.getName());
            author.setImage(request.getImage());
            author.setUpdatedAt(new Date());

            Author savedAuthor = authorRepository.save(author);
            if(savedAuthor == null){
                throw new CustomException("Internal Server Error");
            }

            AuthorResponseDto result = DtoMapper.authorMapper(savedAuthor);
            return GeneralResponse.generateResponse("successfully updated author", HttpStatus.CREATED, result);
        } catch (CustomException e){
            return GeneralResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @Override
    public ResponseEntity<Object> deleteAuthor(Long id) {

        try {
            authorRepository.findById(id).orElseThrow(() -> new CustomException("Author not found with id: " + id));
            authorRepository.deleteById(id);

            return GeneralResponse.generateResponse("successfully deleted author", HttpStatus.OK, null);
        } catch (CustomException e){
            return GeneralResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseEntity<Object> retrieveAuthorByName(String name) {

        try {
            List<Author> authors = authorRepository.findByNameIgnoreCaseContaining(name);
            if (authors.isEmpty()){
                throw new CustomException("Author Not Found with name: " + name);
            }

            List<AuthorResponseDto> authorResponseDto = DtoMapper.authorListMapper(authors);

            return GeneralResponse.generateResponse("successfully retrieve author!", HttpStatus.OK, authorResponseDto);
        } catch (CustomException e){
            return GeneralResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

}
