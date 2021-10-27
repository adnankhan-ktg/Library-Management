package com.intelliatech.LibraryManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private long bookId;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "number_of_pages")
    private long numberOfPages;
    @Column(name = "book_published_date")
    private String bookPublishedDate;
    @ManyToOne
    private Subject subject;
    @OneToOne
    private BookPublisher bookPublisher;
}
