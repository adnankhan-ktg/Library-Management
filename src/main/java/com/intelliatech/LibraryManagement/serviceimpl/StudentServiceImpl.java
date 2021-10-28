package com.intelliatech.LibraryManagement.serviceimpl;

import com.intelliatech.LibraryManagement.dto.StudentDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;
import com.intelliatech.LibraryManagement.model.Student;
import com.intelliatech.LibraryManagement.repository.StudentRepository;
import com.intelliatech.LibraryManagement.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ErrorMessage createStudent(StudentDto studentDto) throws BusinessException {
        log.info("Inside StudentServiceImpl in createStudent()");
        //Check Student already exists or not
        Student student_1 = this.studentRepository.findByEmailOrMobileNumber(studentDto.getEmail(),studentDto.getMobileNumber());
        if(student_1 != null)
        {
            throw new BusinessException(208,"Student already exists");
        }
        //Student Entity Object
         Student student_2 = new Student();
        //StudentDto to Student Entity

        BeanUtils.copyProperties(studentDto,student_2);
        //Set Student Address from StudentDto
        student_2.setAddress(studentDto.getAddress());
        //Save Student Object
        Student student_3 = this.studentRepository.save(student_2);
        if(student_3 != null)
        {
            log.info("leaving createUser() in StudentServiceImpl");
            return new ErrorMessage("Student successfully created",200);
        }else{
            log.info("throw exception");
            throw new BusinessException(400,"Bad Request");
        }
    }

    @Override
    public List<StudentDto> getStudents()throws BusinessException {
           log.info("Inside StudentServiceImpl in getStudents()");
           //database call
           List<Student> listOfStudent = this.studentRepository.findAll();
           //Create StudentDto type of list
           List<StudentDto> listOfStudentDto = new ArrayList<>();
           //Create StudentDto type of Object

        //Transfer value listOfStudent to listOfStudentDto
        for(Student s : listOfStudent)
           {
               StudentDto studentDto = new StudentDto();
               //Student Entity to StudentDto
               BeanUtils.copyProperties(s,studentDto);
               studentDto.setAddress(s.getAddress());
               listOfStudentDto.add(studentDto);
           }
           log.info("Leaving StudentServiceImpl in getStudents()");
           return listOfStudentDto;
    }

    @Override
    public StudentDto getStudent(long studentId) throws BusinessException {
          log.info("Inside StudentServiceImpl in getStudent()");

          //Database code
          Student student_1 = this.studentRepository.findByStudentId(studentId);

          if(student_1 == null)
          {
              throw new BusinessException(404,"Desired Student is not found");
          }else{
              //Create StudentDto type of Object
              StudentDto studentDto = new StudentDto();
              //Student Entity to StudentDto Object
              BeanUtils.copyProperties(student_1, studentDto);
              log.info("Leaving StudentServiceImpl in getStudent()");
              return studentDto;
          }

    }
}
