package com.intelliatech.LibraryManagement.repository;

import com.intelliatech.LibraryManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student,Long> {

      Student findByEmailOrMobileNumber(String email, String mobileNumber);
      Student findByStudentId(long id);
      @Query(value = "SELECT count(*) from student where student_id= :studentId",nativeQuery = true)
      public int checkStudent(long studentId);
}
