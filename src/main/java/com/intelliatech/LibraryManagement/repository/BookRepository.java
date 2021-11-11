package com.intelliatech.LibraryManagement.repository;

import com.intelliatech.LibraryManagement.dto.BookDto;
import com.intelliatech.LibraryManagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByBookId(long bookId);

    @Query(value = "Select * from book b where b.subject_subject_id= :id",nativeQuery = true)
    public List<Book> getBooksBySubjectId(long id);
    List<Book> findByIsAvailable(int isAvailable);

}
