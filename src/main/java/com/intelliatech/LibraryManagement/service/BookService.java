package com.intelliatech.LibraryManagement.service;

import com.intelliatech.LibraryManagement.dto.BookDto;
import com.intelliatech.LibraryManagement.dto.BookIssuedDto;
import com.intelliatech.LibraryManagement.dto.BookIssuedListsDto;
import com.intelliatech.LibraryManagement.dto.StudentBookIssuedDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;

import java.util.List;

public interface BookService {

    ResponseMessage createBook(BookDto bookDto) throws Exception;
    List<BookDto> getBooks() throws BusinessException;
    BookDto getBook(long id) throws BusinessException;
    ResponseMessage studentBookIssued(long studentId, long bookId)throws BusinessException;
    ResponseMessage studentBookReturned(long studentId, long bookId) throws Exception;
    BookIssuedListsDto getIssuedBookRecordsAndReturnedBookRecords(long studentId,int offset, int size) throws Exception;
    List<BookDto> getBooksBySubject(long subjectId) throws BusinessException;
    List<StudentBookIssuedDto> getStudentIssuedBooks(long studentId) throws Exception;
    List<BookDto> getAvailableBooks() throws BusinessException;
    List<BookDto> getUnAvailableBooks() throws BusinessException;
    List<BookDto> getAvailableBooksBySubjectId(long subjectId) throws BusinessException;
    List<BookDto> getUnAvailableBooksBySubjectId(long subjectId) throws BusinessException;
    List<BookIssuedDto> getIssuedBookByDate(String date) throws Exception;




}
