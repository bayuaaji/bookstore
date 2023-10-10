package com.indonesia.bookstore.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDto {

    private String name;

    private String image;

    private List<Long> books;
}
