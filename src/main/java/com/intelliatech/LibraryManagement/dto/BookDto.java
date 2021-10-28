package com.intelliatech.LibraryManagement.dto;

import com.intelliatech.LibraryManagement.model.BookAuthor;
import com.intelliatech.LibraryManagement.model.BookPublisher;
import com.intelliatech.LibraryManagement.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private long bookId;
    private String bookName;
    private long numberOfPages;
    private String bookPublishedDate;
    private boolean isActive;
    private boolean isAvailable;
    private Subject subject;
    private BookPublisher bookPublisher;
    private List<BookAuthor> bookAuthors;
}
