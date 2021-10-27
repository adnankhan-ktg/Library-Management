package com.intelliatech.LibraryManagement.service;

import com.intelliatech.LibraryManagement.dto.StudentDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;

import java.util.List;

public interface StudentService {
    ErrorMessage createStudent(StudentDto studentDto) throws BusinessException;
    List<StudentDto> getStudents()throws BusinessException;
    StudentDto getStudent(long studentId) throws BusinessException;
}
