package com.intelliatech.LibraryManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentBookIssued {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "student_id")
    private long studentId;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "student_date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date studentDateOfBirth;
    @Column(name = "book_id")
    private long bookId;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "book_subject")
    private String bookSubject;
    @Column(name = "book_issued_date")
    @Temporal(TemporalType.DATE)
    private Date bookIssuedDate;
    @Column(name = "book_return_date")
    @Temporal(TemporalType.DATE)
    private Date bookReturnDate;
    @Column(name = "penalty")
    private long penalty;
    @Column(name = "is_returned")
    private int isReturned;
    @Column(name = "is_issued")
    private int isIssued;
}
