package com.indonesia.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String year;

    private String description;

    private String totalPage;

    private String publisher;

    private String image;

    private Float price;

    private Boolean availability;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    private Date createdAt;

    private Date updatedAt;


}
