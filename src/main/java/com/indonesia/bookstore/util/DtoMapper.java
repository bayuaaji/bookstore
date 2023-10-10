package com.indonesia.bookstore.util;

import com.indonesia.bookstore.dto.AuthorRequestDto;
import com.indonesia.bookstore.dto.AuthorResponseDto;
import com.indonesia.bookstore.dto.BookResponseDto;
import com.indonesia.bookstore.model.Author;
import com.indonesia.bookstore.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DtoMapper {

    public static List<BookResponseDto> bookListMapper(List<Book> books){
        return books.stream()
                .filter(e -> e.getAvailability() == true)
                .map(e -> new BookResponseDto(e.getTitle(), e.getYear(), e.getDescription(),
                        e.getTotalPage(), e.getPublisher(), e.getImage(), e.getPrice(), e.getAuthor().getName()))
                .collect(Collectors.toList());
    }

    public static BookResponseDto bookMapper(Book book) {
        return BookResponseDto.builder()
                .title(book.getTitle())
                .description(book.getDescription())
                .year(book.getYear())
                .image(book.getImage())
                .totalPage(book.getTotalPage())
                .publisher(book.getPublisher())
                .price(book.getPrice())
                .author(book.getAuthor().getName())
                .build();
    }

    public static List<AuthorResponseDto> authorListMapper(List<Author> authors){
        return authors.stream()
                .map(e -> new AuthorResponseDto(e.getName(), e.getImage(), bookListMapper(e.getBooks())))
                .toList();
    }

    public static AuthorResponseDto authorMapper(Author author) {
        return AuthorResponseDto.builder()
                .name(author.getName())
                .image(author.getImage())
                .books(bookListMapper(author.getBooks()))
                .build();
    }
}
