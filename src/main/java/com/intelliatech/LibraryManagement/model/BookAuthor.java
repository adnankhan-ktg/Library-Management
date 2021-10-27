package com.intelliatech.LibraryManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_author_id")
    private long authorId;
    private String authorName;
    private String email;
    @OneToOne
    private Address address;
    @ManyToOne
    private Book book;
}
