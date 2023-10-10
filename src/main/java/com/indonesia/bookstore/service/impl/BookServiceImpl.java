package com.indonesia.bookstore.service.impl;

import com.indonesia.bookstore.dto.AuthorRequestDto;
import com.indonesia.bookstore.dto.BookRequestDto;
import com.indonesia.bookstore.dto.BookResponseDto;
import com.indonesia.bookstore.dto.GeneralResponse;
import com.indonesia.bookstore.handler.CustomException;
import com.indonesia.bookstore.model.Author;
import com.indonesia.bookstore.model.Book;
import com.indonesia.bookstore.repository.AuthorRepository;
import com.indonesia.bookstore.repository.BookRepository;
import com.indonesia.bookstore.service.BookService;
import com.indonesia.bookstore.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public ResponseEntity<Object> retrieveAllBooks() {

        try {
            List<Book> books = bookRepository.findAll();

            if(books == null){
                throw new CustomException("Book is empty!");
            }

            List<BookResponseDto> bookResponseDtoList = DtoMapper.bookListMapper(books);

            return GeneralResponse.generateResponse("successfully retrieved all books", HttpStatus.OK, bookResponseDtoList);
        } catch (CustomException e){
            return GeneralResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);

        }

    }

    @Override
    public ResponseEntity<Object> retrieveBookById(Long id) {

        try {

            Optional<Book> bookById = bookRepository.findById(id);
            if(bookById.isEmpty()){
                throw new CustomException("Book not found with id: " + id);
            }

            BookResponseDto result = DtoMapper.bookMapper(bookById.get());
            return GeneralResponse.generateResponse("successfully retrieved book with id: " + id, HttpStatus.OK, result);
        } catch (CustomException e){
            return GeneralResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseEntity<Object> addBook(BookRequestDto request) {
        Author authorData = new Author();
        List<Book> authorBooks = new ArrayList<>();
        try {

            //get author data by name
            List<Author> authorDataList = authorRepository.findByNameIgnoreCaseContaining(request.getAuthorData().getName());

            //if author do not exists in DB, create new author
            if(authorDataList.isEmpty()){
                authorData.setName(request.getAuthorData().getName());
                authorData.setImage(request.getAuthorData().getImage());
                authorData.setCreatedAt(new Date());
                authorRepository.save(authorData);
            } else {
                authorData = authorDataList.get(0);
                authorBooks = authorData.getBooks();
            }


            //set new book object
            Book book = new Book();
            book.setTitle(request.getTitle());
            book.setYear(request.getYear());
            book.setDescription(request.getDescription());
            book.setTotalPage(request.getTotalPage());
            book.setPublisher(request.getPublisher());
            book.setPrice(request.getPrice());
            book.setAvailability(true);
            book.setImage(request.getImage());
            book.setAuthor(authorData);
            book.setCreatedAt(new Date());
            book.setUpdatedAt(null);

            Book savedBook = bookRepository.save(book);

            if(savedBook == null){
                throw new CustomException("Internal Server Error");
            }

            BookResponseDto result = DtoMapper.bookMapper(savedBook);

            //update author table
//            authorBooks.add(result);
//            authorData.setBooks(authorBooks);

            return GeneralResponse.generateResponse("successfully added book", HttpStatus.CREATED, result);
        } catch (CustomException e){
            return GeneralResponse.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Override
    public ResponseEntity<Object> updateBook(BookRequestDto request, Long id) {

        try {
            Book book = bookRepository.findById(id).orElseThrow(() -> new CustomException("Book not found with id: " + id));
            book.setTitle(request.getTitle());
            book.setYear(request.getYear());
            book.setDescription(request.getDescription());
            book.setTotalPage(request.getTotalPage());
            book.setPublisher(request.getPublisher());
            book.setPrice(request.getPrice());
            book.setAvailability(request.getAvailability());
            book.setImage(request.getImage());
            book.setUpdatedAt(new Date());

            Book savedBook = bookRepository.save(book);
            if(savedBook == null){
                throw new CustomException("Internal Server Error");
            }

            BookResponseDto result = DtoMapper.bookMapper(savedBook);
            return GeneralResponse.generateResponse("successfully updated book", HttpStatus.CREATED, result);
        } catch (CustomException e){
            return GeneralResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @Override
    public ResponseEntity<Object> deleteBook(Long id) {

        try {
            bookRepository.findById(id).orElseThrow(() -> new CustomException("Book not found with id: " + id));
            bookRepository.deleteById(id);

            return GeneralResponse.generateResponse("successfully deleted book", HttpStatus.OK, null);
        } catch (CustomException e){
            return GeneralResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseEntity<Object> retrieveBookByTitle(String title) {

        try {
            List<Book> books = bookRepository.findByTitleIgnoreCaseContaining(title);
            if (books.isEmpty()){
                throw new CustomException("Book Not Found with title: " + title);
            }

            List<BookResponseDto> bookResponseDto = DtoMapper.bookListMapper(books);

            return GeneralResponse.generateResponse("successfully retrieve book!", HttpStatus.OK, bookResponseDto);
        } catch (CustomException e){
            return GeneralResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseEntity<Object> retrieveBookByPriceMax(Float price) {
        try {
            List<Book> books = bookRepository.findByPriceLessThanOrEqualNative(price);
            if(books.isEmpty()){
                throw new CustomException("book with price under " + price + " not found!");
            }

            List<BookResponseDto> result = DtoMapper.bookListMapper(books);

            return GeneralResponse.generateResponse("successfully retrieved books with price under " + price, HttpStatus.OK, result);
        } catch (CustomException e){
            return GeneralResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }
}
