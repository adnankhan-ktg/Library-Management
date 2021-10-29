package com.intelliatech.LibraryManagement.controller;

import com.intelliatech.LibraryManagement.dto.BookDto;
import com.intelliatech.LibraryManagement.dto.StudentBookIssuedDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;
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
      public ErrorMessage createBook(@RequestBody BookDto bookDto)throws Exception
      {
          log.info("Inside BookController in createBook()");
          ErrorMessage errorMessage = this.bookService.createBook(bookDto);
          log.info("Leaving BookController in createBook()");
          return errorMessage;
      }

      @GetMapping("/getBooks")
      public List<BookDto> getBooks()throws BusinessException
      {
          log.info("Inside BookController in getBooks()");
          List<BookDto> listOfBookDto = this.bookService.getBooks();
          log.info("Leaving BookController in getBooks()");
          return listOfBookDto;
      }


       @PostMapping("/student/allocate")
      public ErrorMessage studentBookIssued(@RequestBody StudentBookIssuedDto studentBookIssuedDto) throws BusinessException
      {
          log.info("Inside BookController in studentBookIssued()");
          ErrorMessage errorMessage =this.bookService.studentBookIssued(studentBookIssuedDto.getStudentId(), studentBookIssuedDto.getBookId());
          log.info("Leaving BookController in studentBookIssued()");
          return errorMessage;

      }



}
