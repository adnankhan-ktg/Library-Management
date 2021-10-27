package com.intelliatech.LibraryManagement.serviceimpl;

import com.intelliatech.LibraryManagement.dto.BookAuthor;
import com.intelliatech.LibraryManagement.dto.BookDto;
import com.intelliatech.LibraryManagement.dto.StudentDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;
import com.intelliatech.LibraryManagement.model.Book;
import com.intelliatech.LibraryManagement.model.Student;
import com.intelliatech.LibraryManagement.repository.BookRepository;
import com.intelliatech.LibraryManagement.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
     private BookRepository bookRepository;
    @Override
    public ErrorMessage createBook(BookDto bookDto) throws BusinessException {
        log.info("Inside BookServiceImpl in createBook()");
        //Create Book Entity type of Object
        Book book_1 = new Book();
        //Copy value Book Entity to BookDto
        BeanUtils.copyProperties(bookDto,book_1);
        book_1.setBookAuthors(bookDto.getBookAuthors());
        Book book_2 = this.bookRepository.save(book_1);
        if(book_2 == null)
        {
            log.info("throw exception");
            throw new BusinessException(400,"Bad Request");
        }else{
            log.info("Book Successfully created");
            log.info("Leaving BookServiceImpl in createBook()");
            return new ErrorMessage("Book Successfully created",200);
        }



    }

    @Override
    public List<BookDto> getBooks() throws BusinessException {
        return null;
    }
}
