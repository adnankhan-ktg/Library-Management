package com.intelliatech.LibraryManagement.repository;

import com.intelliatech.LibraryManagement.model.StudentBookIssued;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentBookIssuedRepository extends JpaRepository<StudentBookIssued,Long> {

    StudentBookIssued findByStudentIdAndBookId(long studentId,long bookId);
}
