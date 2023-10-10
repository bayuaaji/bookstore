package com.indonesia.bookstore.dto;

import com.indonesia.bookstore.model.Book;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDto {

    private String name;

    private String image;

    private List<BookResponseDto> books;

}
