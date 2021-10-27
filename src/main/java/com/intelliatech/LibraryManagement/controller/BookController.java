package com.intelliatech.LibraryManagement.controller;

import com.intelliatech.LibraryManagement.dto.BookDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;
import com.intelliatech.LibraryManagement.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
     @Autowired
    private BookService bookService;

     @PostMapping("/create")
      public ErrorMessage createBook(@RequestBody BookDto bookDto)throws BusinessException
      {
          log.info("Inside BookController in createBook()");
          ErrorMessage errorMessage = this.bookService.createBook(bookDto);
          log.info("Leaving BookController in createBook()");
          return errorMessage;
      }

}
