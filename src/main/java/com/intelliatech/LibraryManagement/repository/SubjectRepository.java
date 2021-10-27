package com.intelliatech.LibraryManagement.repository;

import com.intelliatech.LibraryManagement.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    Subject findBySubjectCode(long subjectCode);
    Subject findBySubjectId(long subjectId);
}
