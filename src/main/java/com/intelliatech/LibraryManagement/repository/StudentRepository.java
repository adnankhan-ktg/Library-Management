package com.intelliatech.LibraryManagement.repository;

import com.intelliatech.LibraryManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

      Student findByEmailOrMobileNumber(String email, String mobileNumber);
      Student findByStudentId(long id);
}
