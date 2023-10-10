package com.indonesia.bookstore.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {

    private String title;

    private String year;

    private String description;

    private String totalPage;

    private String publisher;

    private String image;

    private Float price;

    private Boolean availability;

    private Long authorId;

    private AuthorRequestDto authorData;

}
