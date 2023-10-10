package com.indonesia.bookstore.controller;

import com.indonesia.bookstore.dto.BookRequestDto;
import com.indonesia.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(path = "/")
    public ResponseEntity<Object> retrieveAllBooks(){
        return bookService.retrieveAllBooks();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> retrieveBookById(@PathVariable(value = "id") Long id){
        return bookService.retrieveBookById(id);
    }

    @GetMapping(path = "/book-title")
    public ResponseEntity<Object> retrieveBookByTitle(@RequestParam(required = false) String title){
        return bookService.retrieveBookByTitle(title);
    }

    @GetMapping(path = "/max-price")
    public ResponseEntity<Object> retrieveBookByMaxPrice(@RequestParam(required = false) Float price){
        return bookService.retrieveBookByPriceMax(price);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Object> addBook(@RequestBody BookRequestDto request){
        return bookService.addBook(request);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateBook(@RequestBody BookRequestDto request, @PathVariable(value = "id") Long id){
        return bookService.updateBook(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") Long id){
        return bookService.deleteBook(id);
    }

}
