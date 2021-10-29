package com.intelliatech.LibraryManagement.service;

import com.intelliatech.LibraryManagement.dto.BookDto;
import com.intelliatech.LibraryManagement.dto.BookIssuedDto;
import com.intelliatech.LibraryManagement.dto.BookIssuedListsDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;
import com.intelliatech.LibraryManagement.model.Book;

import java.util.List;

public interface BookService {

    ErrorMessage createBook(BookDto bookDto) throws Exception;
    List<BookDto> getBooks() throws BusinessException;
    BookDto getBook(long id) throws BusinessException;
    ErrorMessage studentBookIssued(long studentId,long bookId)throws BusinessException;
    ErrorMessage studentBookReturned(long studentId, long bookId) throws Exception;
    BookIssuedListsDto getIssuedBookRecordsAndReturnedBookRecords(long studentId) throws Exception;



}
