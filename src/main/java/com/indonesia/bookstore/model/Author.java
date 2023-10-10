package com.indonesia.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "author",
            fetch = FetchType.LAZY)
    private List<Book> books;

    private Date createdAt;

    private Date updatedAt;

}
