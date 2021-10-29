package com.intelliatech.LibraryManagement.repository;

import com.intelliatech.LibraryManagement.model.StudentBookIssued;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentBookIssuedRepository extends JpaRepository<StudentBookIssued,Long> {

    StudentBookIssued findByStudentIdAndBookId(long studentId,long bookId);
    List<StudentBookIssued> findByStudentIdAndIsIssued(long studentId, int isIssued);
    List<StudentBookIssued> findByStudentIdAndIsReturned(long studentId,int isReturned);


}