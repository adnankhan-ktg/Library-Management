package com.intelliatech.LibraryManagement.controller;


import com.intelliatech.LibraryManagement.dto.StudentDto;
import com.intelliatech.LibraryManagement.dto.StudentDtoNew;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;
import com.intelliatech.LibraryManagement.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    //For Only Json data
    @PostMapping("/create")
    public ErrorMessage createStudent(@RequestBody StudentDto studentDto) throws Exception
    {
        log.info("Inside StudentController in createStudent()");
         ErrorMessage errorMessage = this.studentService.createStudent(studentDto);
         log.info("Leaving StudentController in createStudent()");
         return errorMessage;

    }

    //For MultipartFile and Json as well....
    @PostMapping("/create/new")
    public ErrorMessage createStudent_1(@RequestParam("file") MultipartFile multipartFile, StudentDtoNew studentDtoNew) throws Exception
    {
        log.info("Inside StudentController in createStudent()");
        ErrorMessage errorMessage = this.studentService.createStudent_1(multipartFile,studentDtoNew);
        log.info("Leaving StudentController in createStudent()");
        return errorMessage;

    }

    @GetMapping("/getStudents")
    public List<StudentDto> getStudents() throws BusinessException
    {
        log.info("Inside StudentController in getStudent()");
        List<StudentDto> listOfStudentDto = this.studentService.getStudents();
        log.info("Leaving StudentController in getStudent()");
        return listOfStudentDto;
    }

    @GetMapping("/getStudent/{studentId}")
    public StudentDto getStudent(@PathVariable("studentId") long studentId)throws BusinessException
    {
        log.info("Inside StudentController in getStudent()");
        StudentDto studentDto = this.studentService.getStudent(studentId);
        log.info("Leaving StudentController in getStudent()");
        return studentDto;
    }

}
