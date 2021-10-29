package com.intelliatech.LibraryManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookIssuedDto {


    private long id;
    private long studentId;
    private String studentName;
    private Date studentDateOfBirth;
    private long bookId;
    private String bookName;
    private String bookSubject;
    private Date bookIssuedDate;
    private Date bookReturnDate;
    private long penalty;
    private int isReturned;
    private int isIssued;
}
