package com.intelliatech.LibraryManagement.service;

import com.intelliatech.LibraryManagement.dto.StudentDto;
import com.intelliatech.LibraryManagement.dto.StudentDtoNew;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    ResponseMessage createStudent(StudentDto studentDto) throws Exception;
    ResponseMessage createStudent_1(MultipartFile multipartFile , StudentDtoNew studentDtoNew) throws Exception;
    List<StudentDto> getStudents(Integer offset, Integer size)throws BusinessException;
    StudentDto getStudent(long studentId) throws BusinessException;
}
