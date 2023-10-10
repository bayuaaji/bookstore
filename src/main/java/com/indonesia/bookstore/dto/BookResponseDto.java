package com.indonesia.bookstore.dto;

import com.indonesia.bookstore.model.Author;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {

    private String title;

    private String year;

    private String description;

    private String totalPage;

    private String publisher;

    private String image;

    private Float price;

    private String author;

}
