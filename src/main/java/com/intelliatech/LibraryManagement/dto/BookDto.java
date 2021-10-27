package com.intelliatech.LibraryManagement.dto;

import com.intelliatech.LibraryManagement.model.BookPublisher;
import com.intelliatech.LibraryManagement.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private long bookId;
    private String bookName;
    private long numberOfPages;
    private String bookPublishedDate;
    private Subject subject;
    private BookPublisher bookPublisher;
}
