package com.intelliatech.LibraryManagement.serviceimpl;

import com.intelliatech.LibraryManagement.dto.MailRequestDto;
import com.intelliatech.LibraryManagement.dto.StudentDto;
import com.intelliatech.LibraryManagement.dto.StudentDtoNew;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;
import com.intelliatech.LibraryManagement.model.Student;
import com.intelliatech.LibraryManagement.repository.StudentRepository;
import com.intelliatech.LibraryManagement.service.StudentService;
import com.intelliatech.LibraryManagement.service.helper.FileUploadHelper;
import com.intelliatech.LibraryManagement.service.helper.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private FileUploadHelper fileUploadHelper;

    @Override
    public ResponseMessage createStudent(StudentDto studentDto) throws Exception {
        log.info("Inside StudentServiceImpl in createStudent()");
        //Check Student already exists or not
        Student student_1 = this.studentRepository.findByEmailOrMobileNumber(studentDto.getEmail(),studentDto.getMobileNumber());
        if(student_1 != null)
        {
            throw new BusinessException(208,"Student already exists");
        }
        //Student Entity Object
         Student student_2 = new Student();

        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(studentDto.getDateOfBirth());
        student_2.setDateOfBirth(date1);

        //StudentDto to Student Entity
        BeanUtils.copyProperties(studentDto,student_2);
        //Set Student Address from StudentDto
        student_2.setAddress(studentDto.getAddress());
        //Set 1 for active student and 0 of deActive student
        student_2.setIsActive(1);

        student_2.setRegistrationDate(new Date());

        //Save Student Object
        Student student_3 = this.studentRepository.save(student_2);
        if(student_3 != null)
        {
            log.info("Sent student registration notification to the student");
            //Sent notification to the student for registration
            mailService.sendMail(new MailRequestDto(student_3.getEmail(),student_3.getFirstName()+" "+student_3.getLastName()+" Your Library account successfully created","Account Registration"));

            log.info("leaving createUser() in StudentServiceImpl");
            return new ResponseMessage("Student successfully created",200);
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

    @Override
    public ResponseMessage createStudent_1(MultipartFile multipartFile, StudentDtoNew studentDto) throws Exception {
        log.info("Inside StudentServiceImpl in createStudent()");
        //Check Student already exists or not
        Student student_1 = this.studentRepository.findByEmailOrMobileNumber(studentDto.getEmail(),studentDto.getMobileNumber());
        if(student_1 != null)
        {
            throw new BusinessException(208,"Student already exists");
        }
        //Student Entity Object
        Student student_2 = new Student();

        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(studentDto.getDateOfBirth());
        student_2.setDateOfBirth(date1);

        //StudentDto to Student Entity
        BeanUtils.copyProperties(studentDto,student_2);
        //Set 1 for active student and 0 of deActive student
        student_2.setIsActive(1);
        student_2.setRegistrationDate(new Date());

        //File Upload..
           if(fileUploadHelper.uploadFile(multipartFile)) {
               student_2.setProfilePicture(fileUploadHelper.UPLOAD_DIR +"\\_"+student_2.getStudentId() + multipartFile.getOriginalFilename());
           }
        //Save Student Object
        Student student_3 = this.studentRepository.save(student_2);
        if(student_3 != null)
        {
            log.info("Sent student registration notification to the student");
//            Sent notification to the student for registration
            mailService.sendMail(new MailRequestDto(student_3.getEmail(),student_3.getFirstName()+" "+student_3.getLastName()+" Your Library account successfully created","Account Registration"));

            log.info("leaving createUser() in StudentServiceImpl");
            return new ResponseMessage("Student successfully created",200);
        }else{
            log.info("throw exception");
            throw new BusinessException(400,"Bad Request");
        }
    }
}
