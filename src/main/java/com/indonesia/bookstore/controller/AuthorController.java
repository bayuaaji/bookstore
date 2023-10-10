package com.indonesia.bookstore.controller;

import com.indonesia.bookstore.dto.AuthorRequestDto;
import com.indonesia.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping(path = "/")
    public ResponseEntity<Object> retrieveAllAuthors(){
        return authorService.retrieveAllAuthors();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> retrieveAuthorById(@PathVariable(value = "id") Long id){
        return authorService.retrieveAuthorById(id);
    }

    @GetMapping(path = "/author-name")
    public ResponseEntity<Object> retrieveAuthorByName(@RequestParam(required = false) String name){
        return authorService.retrieveAuthorByName(name);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Object> addAuthor(@RequestBody AuthorRequestDto request){
        return authorService.addAuthor(request);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateAuthor(@RequestBody AuthorRequestDto request, @PathVariable(value = "id") Long id){
        return authorService.updateAuthor(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable(value = "id") Long id){
        return authorService.deleteAuthor(id);
    }

}
