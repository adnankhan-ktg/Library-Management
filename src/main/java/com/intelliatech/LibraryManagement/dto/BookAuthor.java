package com.intelliatech.LibraryManagement.dto;

import com.intelliatech.LibraryManagement.model.Address;
import com.intelliatech.LibraryManagement.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAuthor {

    private long authorId;
    private String authorName;
    private String email;
    private Address address;
    @ManyToOne
    private Book book;
}
