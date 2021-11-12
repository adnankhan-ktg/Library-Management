package com.intelliatech.LibraryManagement.repository;

import com.intelliatech.LibraryManagement.dto.BookIssuedDto;
import com.intelliatech.LibraryManagement.dto.StudentBookIssuedDto;
import com.intelliatech.LibraryManagement.model.StudentBookIssued;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StudentBookIssuedRepository extends JpaRepository<StudentBookIssued,Long> {

    StudentBookIssued findByStudentIdAndBookIdAndIsIssued(long studentId,long bookId,int isIssued);
    List<StudentBookIssued> findByStudentIdAndIsIssued(long studentId, int isIssued, Pageable pageable);
//    Page<StudentBookIssued> findByStudentIdAndIsIssued(long studentId, int isIssued, Pageable pageable);
    List<StudentBookIssued> findByStudentIdAndIsReturned(long studentId,int isReturned);
    List<StudentBookIssued> findByStudentIdAndIsIssued(long studentId, int isIssued)throws Exception;
    List<StudentBookIssued> findByBookIssuedDate(Date date);


}
