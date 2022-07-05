package com.huyraw.demo.repository;

import com.huyraw.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT s FROM Book s WHERE s.title = ?1")
    Optional<Book> findBookByName(String name);



}
