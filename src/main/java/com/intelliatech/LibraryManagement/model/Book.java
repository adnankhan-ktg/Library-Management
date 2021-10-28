package com.intelliatech.LibraryManagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "is_available")
    private boolean isAvailable;
    @ManyToOne
    private Subject subject;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private BookPublisher bookPublisher;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "book")
    @JsonManagedReference
    private List<BookAuthor> bookAuthors;

    public void setBookAuthors(List<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
        for(BookAuthor auth : bookAuthors){
            //  RepositoryUtils.populateInsertInfo(auth);
            auth.setBook(this);
        }
    }
}
