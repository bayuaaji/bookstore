package com.indonesia.bookstore.repository;

import com.indonesia.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByTitleIgnoreCaseContaining(String title);

    @Query(value = "SELECT * FROM BOOKS b WHERE b.price <= :price", nativeQuery = true)
    List<Book> findByPriceLessThanOrEqualNative(@Param("price") Float price);
}
