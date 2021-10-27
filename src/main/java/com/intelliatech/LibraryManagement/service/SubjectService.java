package com.intelliatech.LibraryManagement.service;

import com.intelliatech.LibraryManagement.dto.SubjectDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;

import java.util.List;

public interface SubjectService {

    ErrorMessage createSubject(SubjectDto subjectDto) throws BusinessException;
    List<SubjectDto> getSubjects() throws BusinessException;
    SubjectDto getSubject(long subjectId) throws BusinessException;
}
