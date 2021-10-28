package com.intelliatech.LibraryManagement.serviceimpl;

import com.intelliatech.LibraryManagement.dto.BookDto;
import com.intelliatech.LibraryManagement.dto.StudentDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;
import com.intelliatech.LibraryManagement.model.Book;
import com.intelliatech.LibraryManagement.model.Student;
import com.intelliatech.LibraryManagement.model.StudentBookIssued;
import com.intelliatech.LibraryManagement.repository.BookRepository;
import com.intelliatech.LibraryManagement.repository.StudentBookIssuedRepository;
import com.intelliatech.LibraryManagement.repository.StudentRepository;
import com.intelliatech.LibraryManagement.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
     private BookRepository bookRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentBookIssuedRepository studentBookIssuedRepository;
    @Override
    public ErrorMessage createBook(BookDto bookDto) throws BusinessException {
        log.info("Inside BookServiceImpl in createBook()");

        //Create Book Entity type of Object
        Book book_1 = new Book();

        //Copy value Book Entity to BookDto
        BeanUtils.copyProperties(bookDto,book_1);

         //Set number of authors in the Book entity
        book_1.setBookAuthors(bookDto.getBookAuthors());
        book_1.setIsAvailable(1);
        book_1.setIsActive(1);
        //Database call
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
        log.info("Inside BookServiceImpl in getBooks()");
        List<Book> listOfBook = this.bookRepository.findAll();
        if(listOfBook == null)
        {
            log.info("Throw Exception");
            throw new BusinessException(404,"Data not found");
        }

        //Create BookDto type of ArrayList
        List<BookDto> listOfBookDto = new ArrayList<>();

        //Transfer value listOfBook to listOfBookDto
        for(Book book : listOfBook)
        {
            BookDto bookDto = new BookDto();
            //Book Entity to BookDto
            BeanUtils.copyProperties(book,bookDto);
            //Add BookDto into the list
            listOfBookDto.add(bookDto);
        }


        log.info("Leaving BookServiceImpl in getBooks()");
        return listOfBookDto;

    }


    @Override
    public ErrorMessage studentBookIssued(long studentId, long bookId)throws BusinessException {
        log.info("Inside StudentImpl in studentBookIssued()");
        //Get book by bookId
        Book book = this.bookRepository.findByBookId(bookId);
        //Get student by studentId
        Student student = this.studentRepository.findByStudentId(studentId);

        //check student number of book issued(out of limit or not)
        if(student.getNumberOfBookIssued() >= 5)
        {
            throw new BusinessException(400,student.getFirstName()+"has maximum number of books");
        }
        //Check book status is it available or active or not
        else if(book.getIsAvailable() == 0 || book.getIsActive() == 0)
        {
            throw new BusinessException(204,"Book already issued");
        }else{
            //Create StudentBookIssued type of 'Object'
            StudentBookIssued studentBookIssued =  new StudentBookIssued();

            //Set Student basic properties in the studentBookIssued Object
            studentBookIssued.setStudentId(student.getStudentId());
            studentBookIssued.setStudentName(student.getFirstName()+" "+student.getLastName());

            //Set Book basic propertied in the studentBookIssued Object
            studentBookIssued.setBookId(book.getBookId());
            studentBookIssued.setBookName(book.getBookName());
            studentBookIssued.setBookSubject(book.getSubject().getSubjectName());
            studentBookIssued.setBookIssuedDate(new Date());
//            studentBookIssued.setBookReturnDate(strDate);
            studentBookIssued.setPenalty(0);
            //update book
            book.setIsAvailable(0);
            //Update student
            student.setNumberOfBookIssued(student.getNumberOfBookIssued() + 1);

            //database call for student
            this.studentRepository.save(student);
            //database call for book
            this.bookRepository.save(book);

            //database call for StudentBookIssued type of table...
            StudentBookIssued studentBookIssued1 = this.studentBookIssuedRepository.save(studentBookIssued);
            if(studentBookIssued1 == null)
            {
                log.info("Throw Exception");
                throw new BusinessException(400,"Bad Request");
            }else{
                log.info("Leaving BookServiceImpl studentBookIssued()");
                return new ErrorMessage("The record succesfully added",200);
            }


        }


    }
}
