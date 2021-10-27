package com.intelliatech.LibraryManagement.repository;

import com.intelliatech.LibraryManagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByBookId(long bookId);
}