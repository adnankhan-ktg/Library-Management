package com.intelliatech.LibraryManagement.controller;

import com.intelliatech.LibraryManagement.dto.*;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;
import com.intelliatech.LibraryManagement.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
     @Autowired
    private BookService bookService;

     @PostMapping("/create")
      public ResponseMessage createBook(@RequestBody BookDto bookDto)throws Exception
      {
          log.info("Inside BookController in createBook()");
          ResponseMessage responseMessage = this.bookService.createBook(bookDto);
          log.info("Leaving BookController in createBook()");
          return responseMessage;
      }

      @GetMapping("/getBooks")
      public List<BookDto> getBooks()throws BusinessException
      {
          log.info("Inside BookController in getBooks()");
          List<BookDto> listOfBookDto = this.bookService.getBooks();
          log.info("Leaving BookController in getBooks()");
          return listOfBookDto;
      }

      @GetMapping("/getBook/{bookId}")
      public BookDto getBook(@PathVariable("bookId") long bookId)throws BusinessException
      {
          log.info("Inside BookController in getBook()");
          BookDto bookDto = this.bookService.getBook(bookId);
          log.info("Leaving BookController in getBook()");
          return bookDto;
      }




       @PostMapping("/student/allocate")
      public ResponseMessage studentBookIssued(@RequestBody StudentBookIssuedDto studentBookIssuedDto) throws BusinessException
      {
          log.info("Inside BookController in studentBookIssued()");
          ResponseMessage responseMessage =this.bookService.studentBookIssued(studentBookIssuedDto.getStudentId(), studentBookIssuedDto.getBookId());
          log.info("Leaving BookController in studentBookIssued()");
          return responseMessage;

      }

      @PostMapping("/student/deAllocate")
      public ResponseMessage studentBookReturned(@RequestBody StudentBookReturnedDto studentBookReturnedDto) throws Exception
      {
          log.info("Inside BookController in studentBookReturned()");
          ResponseMessage responseMessage = this.bookService.studentBookReturned(studentBookReturnedDto.getStudentId(),studentBookReturnedDto.getBookId());
          log.info("Leaving BookController in studentBookReturned()");
          return responseMessage;
      }

      @GetMapping("/get/assigned/returned/books/{studentId}/{offset}/{size}")
      public BookIssuedListsDto getIssuedBookRecord(@PathVariable("studentId") long studentId, @PathVariable("offset") int offset, @PathVariable("size") int size) throws Exception
      {
          log.info("Inside BookController in getIssuedBookRecord()");
          BookIssuedListsDto bookIssuedDtos = this.bookService.getIssuedBookRecordsAndReturnedBookRecords(studentId, offset, size);
          log.info("Leaving BookController in getIssuedBookRecord()");
          return bookIssuedDtos;
      }


      @GetMapping("/get/subjectId/{subjectId}")
      public List<BookDto> getBooksBySubjectId(@PathVariable("subjectId") long subjectId) throws BusinessException
      {
          log.info("Inside BookController in getBooksBySubjectId()");
          List<BookDto> listOfBookDto = this.bookService.getBooksBySubject(subjectId);
          log.info("Leaving BookController in getBooksBySubjectId()");
          return listOfBookDto;

      }


      @GetMapping("/get/all/available")
      public List<BookDto> getAllAvailableBook() throws BusinessException
      {
          log.info("Inside BookController in getAllAvailableBook()");
          List<BookDto> listOfBookDto = this.bookService.getAvailableBooks();
          log.info("Leaving BookController in getAllAvailableBook()");
          return listOfBookDto;

      }

      @GetMapping("/get/all/available/subject/{subjectId}")
       public List<BookDto> getAllAvailableBookBySubjectId(@PathVariable("subjectId") long subjectId) throws BusinessException
       {
           log.info("Inside BookController in getAllAvailableBookBySubjectId()");
           List<BookDto> listOfBookDto = this.bookService.getAvailableBooksBySubjectId(subjectId);
           log.info("Leaving BookController in getAllAvailableBookBySubjectId()");
           return listOfBookDto;
       }

    @GetMapping("/get/all/unavailable")
    public List<BookDto> getAllUnAvailableBook() throws BusinessException
    {
        log.info("Inside BookController in getAllAvailableBook()");
        List<BookDto> listOfBookDto = this.bookService.getUnAvailableBooks();
        log.info("Leaving BookController in getAllAvailableBook()");
        return listOfBookDto;

    }

    @GetMapping("/get/all/unavailable/subject/{subjectId}")
    public List<BookDto> getAllUnAvailableBookBySubjectId(@PathVariable("subjectId") long subjectId) throws BusinessException
    {
        log.info("Inside BookController in getAllAvailableBookBySubjectId()");
        List<BookDto> listOfBookDto = this.bookService.getUnAvailableBooksBySubjectId(subjectId);
        log.info("Leaving BookController in getAllAvailableBookBySubjectId()");
        return listOfBookDto;
    }




}
