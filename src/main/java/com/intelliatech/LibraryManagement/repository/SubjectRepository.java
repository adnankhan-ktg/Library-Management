package com.intelliatech.LibraryManagement.repository;

import com.intelliatech.LibraryManagement.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    Subject findBySubjectCode(long subjectCode);
    Subject findBySubjectId(long subjectId);
    @Query(value = "Select count(*) from subject where subject_id= :subjectId",nativeQuery = true)
    public int checkSubject(long subjectId);

}
