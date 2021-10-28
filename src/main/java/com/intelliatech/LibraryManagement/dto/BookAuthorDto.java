package com.intelliatech.LibraryManagement.dto;

import com.intelliatech.LibraryManagement.model.Address;
import com.intelliatech.LibraryManagement.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAuthorDto {

    private long authorId;
    private String authorName;
    private Book book;
}
