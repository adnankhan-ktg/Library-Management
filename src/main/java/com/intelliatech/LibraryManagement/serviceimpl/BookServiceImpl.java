package com.intelliatech.LibraryManagement.serviceimpl;

import com.intelliatech.LibraryManagement.dto.*;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;
import com.intelliatech.LibraryManagement.model.Book;
import com.intelliatech.LibraryManagement.model.Student;
import com.intelliatech.LibraryManagement.model.StudentBookIssued;
import com.intelliatech.LibraryManagement.repository.BookRepository;
import com.intelliatech.LibraryManagement.repository.StudentBookIssuedRepository;
import com.intelliatech.LibraryManagement.repository.StudentRepository;
import com.intelliatech.LibraryManagement.service.BookService;
import com.intelliatech.LibraryManagement.service.helper.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private MailService mailService;
    @Override
    public ResponseMessage createBook(BookDto bookDto) throws Exception {
        log.info("Inside BookServiceImpl in createBook()");

        //Create Book Entity type of Object
        Book book_1 = new Book();

        //Copy value Book Entity to BookDto
        BeanUtils.copyProperties(bookDto,book_1);

         //Set number of authors in the Book entity
        book_1.setBookAuthors(bookDto.getBookAuthors());
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(bookDto.getBookPublishedDate());
        book_1.setBookPublishedDate(date1);
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
            return new ResponseMessage("Book Successfully created",200);
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
    public ResponseMessage studentBookIssued(long studentId, long bookId)throws BusinessException {
        log.info("Inside StudentImpl in studentBookIssued()");
        //Get book by bookId
        Book book = this.bookRepository.findByBookId(bookId);
        //Get student by studentId
        Student student = this.studentRepository.findByStudentId(studentId);
        //Check student exists or not
        if(student == null)
        {
            throw new BusinessException(404,"Student not found");
        }
        //Check book exists or not
        if(book == null)
        {
            throw new BusinessException(404,"Book not found");
        }

        //check student number of book issued(out of limit or not)
        if(student.getNumberOfBookIssued() >= 5)
        {
            throw new BusinessException(400,student.getFirstName()+" has maximum number of books");
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
            studentBookIssued.setIsIssued(1);
            studentBookIssued.setIsReturned(0);
            studentBookIssued.setStudentDateOfBirth(student.getDateOfBirth());
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
//                mailService.sendMail(new MailRequestDto(student.getEmail(),student.getFirstName()+" "+student.getLastName()+" Book "+book.getBookName()+" Issued to you","Book Issued"));
                return new ResponseMessage("The record successfully added",200);
            }


        }


    }


    @Override
    public ResponseMessage studentBookReturned(long studentId, long bookId) throws Exception {
        log.info("Inside BooServiceImpl in studentBookReturned()");
        StudentBookIssued studentBookIssued = this.studentBookIssuedRepository.findByStudentIdAndBookIdAndIsIssued(studentId,bookId,1);
        if(studentBookIssued == null)
        {
            throw new BusinessException(404,"Record Not Found");
        }
        if(studentBookIssued.getIsIssued() != 1 || studentBookIssued.getIsReturned() != 0)
        {
            throw new BusinessException(420,"Book not Assigned");
        }

        //Get Book Issued Date from studentBookIssued
        Date bookIssuedDate = studentBookIssued.getBookIssuedDate();
        //Assign current date
        Date bookReturnedDate = new Date();
        //Set book returned date in the entity
        studentBookIssued.setBookReturnDate(new Date());
        //Calculate book assigned days
        long difference_In_Time = bookReturnedDate.getTime() - bookIssuedDate.getTime();
        long difference_In_Days = (difference_In_Time/(1000 * 60 * 60 * 24)) % 365;
        //Check issued days over or not
        long extraDays = 0;
        if (difference_In_Days > 7)
        {
            extraDays = difference_In_Days - 7;
            studentBookIssued.setPenalty(extraDays * 10);
        }
        //Set isIssued status by not(0)
        studentBookIssued.setIsIssued(0);
        //Set IsReturned status by yes(1)
        studentBookIssued.setIsReturned(1);
        //Get Student Object from database on the basis of studentId
        Student student = this.studentRepository.findByStudentId(studentId);
        student.setNumberOfBookIssued(student.getNumberOfBookIssued() - 1);
        student.setTotalPenalty(student.getTotalPenalty() + extraDays * 10);
        //Get Book Object from database on the basis of bookId
        Book book = this.bookRepository.findByBookId(bookId);
        book.setIsAvailable(1);
        //Update the Records
        this.bookRepository.save(book);
        this.studentRepository.save(student);
        this.studentBookIssuedRepository.save(studentBookIssued);




//        mailService.sendMail(new MailRequestDto(student.getEmail(),student.getFirstName()+" "+student.getLastName()+" Book "+book.getBookName()+" deallocate to you","Book Issued"));
     return new ResponseMessage("Successfully Book Returned",200);


    }

    @Override
    public BookDto getBook(long id) throws BusinessException {
        log.info("Inside BookServiceImpl in getBook()");
         Book book = this.bookRepository.findByBookId(id);
        if(book == null)
        {
            log.error("Throw exception book not found");
            throw new BusinessException(404,"Book Not Found");
        }
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book,bookDto);
        log.info("Leaving BookServiceImpl getBook()");
        return bookDto;
    }

    @Override
    public BookIssuedListsDto getIssuedBookRecordsAndReturnedBookRecords(long studentId, int offset, int size) throws Exception{
            log.info("Inside BookServiceImpl in getIssuedBookRecord()");

            //Make PageRequest Object
             Pageable pageable = PageRequest.of(offset,size,Sort.by("bookId").ascending());
             //Fetch Issued book records for specific student
             List<StudentBookIssued> listOfIssuedBook = this.studentBookIssuedRepository.findByStudentIdAndIsIssued(studentId,1,pageable);
             List<StudentBookIssued> listOfReturnedBook = this.studentBookIssuedRepository.findByStudentIdAndIsReturned(studentId,1);


             //Create BookIssuedDto type of List
             List<BookIssuedDto> listOfIssuedBookDto = new ArrayList<>();
             List<BookIssuedDto> listOfReturnedBookDto = new ArrayList<>();

             //Copy list of StudentBookIssued Entity to StudentBookIssuedDto
             if(listOfIssuedBook != null) {
                 for(StudentBookIssued record : listOfIssuedBook) {
                     BookIssuedDto bookIssuedDto = new BookIssuedDto();
                     BeanUtils.copyProperties(record, bookIssuedDto);
                     listOfIssuedBookDto.add(bookIssuedDto);
                 }
             }


            if(listOfReturnedBook != null) {
            for(StudentBookIssued record : listOfReturnedBook) {
                BookIssuedDto bookIssuedDto = new BookIssuedDto();
                BeanUtils.copyProperties(record, bookIssuedDto);
                listOfReturnedBookDto.add(bookIssuedDto);
            }
        }
            //Create BookIssuedListsDto type of Object
        BookIssuedListsDto bookIssuedListsDto = new BookIssuedListsDto();

        //Add both list in the BookIssuedListsDto type of Object
        bookIssuedListsDto.setListOfIssuedBook(listOfIssuedBookDto);
        bookIssuedListsDto.setListOfReturnedBook(listOfReturnedBookDto);

        log.info("Leaving BookServiceImpl in getIssuedBookRecord()");
        return bookIssuedListsDto;


    }


    @Override
    public List<BookDto> getBooksBySubject(long subjectId) throws BusinessException {
        log.info("Inside BookServiceImpl in getBooksBySubject()");
        List<Book> listOfBook = this.bookRepository.getBooksBySubjectId(subjectId);
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
    public List<StudentBookIssuedDto> getStudentIssuedBooks(long studentId)throws Exception {
        log.info("Inside BookServiceImpl in getStudentIssuedBooks()");
        //Check Student Exists or not with given StudentId
        if(this.studentRepository.checkStudent(studentId) == 0){
            log.info("Student Not found Exception throw");
            throw new BusinessException(404,"Student Not found with Student Id");
        }
        //Database Call
        //Get List of Issued Book with studentId and BookId
        List<StudentBookIssued> listOfIssuedBook = this.studentBookIssuedRepository.findByStudentIdAndIsIssued(studentId,1);
        //Check Student has at least  one book or not
        if(listOfIssuedBook.size() == 0)
        {
            log.info("Throw Exception");
            throw new BusinessException(404,"Data not found");
        }
        //Create StudentBookIssuedDto type of list
        List<StudentBookIssuedDto> listOfIssuedBookDto = new ArrayList<>();
        //Write code for change list of entity to dto
        for(StudentBookIssued i : listOfIssuedBook){
            //Create studentBookIssuedDto Object
            StudentBookIssuedDto studentBookIssuedDto = new StudentBookIssuedDto();
            //Copy value entity to dto
            BeanUtils.copyProperties(i,studentBookIssuedDto);
            //add value in the list
            listOfIssuedBookDto.add(studentBookIssuedDto);
        }
        log.info("Leaving BookServiceImpl in getStudentIssuedBooks()");
        return listOfIssuedBookDto;
    }

    @Override
    public List<BookDto> getAvailableBooks() throws BusinessException {
        log.info("Inside BookServiceImpl in getAvailableBooks()");
        //Database Call
        //Get list of all available book
        List<Book> listOfBook = this.bookRepository.findByIsAvailable(1);
        //Check list has at least one book or not
        if(listOfBook.size() == 0)
        {
            throw new BusinessException(404,"No Data Found");
        }

        //Create BookDto type of List
        List<BookDto> listOfBookDto = new ArrayList<>();
        //Copy list of Book to BookDto
        for(Book book : listOfBook)
        {
            //Create Book type of Object
            BookDto bookDto = new BookDto();
            //Copy properties Book Entity to Book Dto
            BeanUtils.copyProperties(book,bookDto);
            //Add BookDto into the list
            listOfBookDto.add(bookDto);
        }
        log.info("Leaving BookServiceImpl in getAvailableBooks()");
        return listOfBookDto;
    }
}
