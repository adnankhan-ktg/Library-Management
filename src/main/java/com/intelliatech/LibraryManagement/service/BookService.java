package com.intelliatech.LibraryManagement.service;

import com.intelliatech.LibraryManagement.dto.BookDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;
import com.intelliatech.LibraryManagement.model.Book;

import java.util.List;

public interface BookService {

    ErrorMessage createBook(BookDto bookDto) throws BusinessException;
    List<BookDto> getBooks() throws BusinessException;
    ErrorMessage studentBookIssued(long studentId,long bookId)throws BusinessException;


}
