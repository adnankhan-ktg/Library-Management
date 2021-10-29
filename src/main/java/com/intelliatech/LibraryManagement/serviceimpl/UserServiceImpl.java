package com.intelliatech.LibraryManagement.serviceimpl;

import com.intelliatech.LibraryManagement.dto.LoginDto;
import com.intelliatech.LibraryManagement.dto.StudentDto;
import com.intelliatech.LibraryManagement.dto.UserDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;
import com.intelliatech.LibraryManagement.model.Student;
import com.intelliatech.LibraryManagement.model.User;
import com.intelliatech.LibraryManagement.repository.UserRepository;
import com.intelliatech.LibraryManagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public ErrorMessage createUser(UserDto userDto) throws Exception{
         log.info("Inside UserServiceImpl in createUser()");

          //Check User already exists or not
          User user_2 = this.userRepository.findByUsernameOrEmailOrMobileNumber(userDto.getUsername(),userDto.getEmail(),userDto.getMobileNumber());
          if(user_2 != null)
          {
              throw new BusinessException(208,"User already exists");
          }
          //Compare new password and confirm password
        if((userDto.getNewPassword().equals(userDto.getConfirmPassword())) == false)
           {
               throw new BusinessException(406,"password doesn't mathe");
           }
        //UserEntity Object
        User user = new User();
        //UserDto to User Entity
        BeanUtils.copyProperties(userDto,user);
        user.setAddress(userDto.getAddress());
        user.setPassword(userDto.getConfirmPassword());
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(userDto.getDateOfBirth());
        user.setDateOfBirth(date1);
        user.setRegistrationDate(new Date());
        //Make User username password in the encrypted form
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user_1 = this.userRepository.save(user);
        if(user_1 != null)
        {
            log.info("leaving createUser() in UserServiceImpl");
           return new ErrorMessage("user successfully created",200);
        }else{
            log.info("throw exception");
           throw new BusinessException(400,"Bad Request");
        }

    }

    @Override
    public ErrorMessage login(LoginDto loginDto) throws  BusinessException{
        log.info("Inside UserServiceImpl in login()");
        User user_1 = this.userRepository.findByUsernameOrEmailOrMobileNumber(loginDto.getUsername(),loginDto.getUsername(),loginDto.getUsername());
        if(user_1 != null)
        {
            if(loginDto.getPassword().equals(user_1.getPassword()))
            {
                log.info("leaving UserServiceImpl in login()");
                return new ErrorMessage("User login successfully",200);
            }else{
                log.info("Throw exception username exists but password not match");
                throw new BusinessException(406,"Password not acceptable corresponding to username");
            }
        }else{
            log.info("Throw exception username doesn't exists");
            throw new BusinessException(404,"Username doesn't exists");
        }
    }

    @Override
    public List<UserDto> getUsers() throws BusinessException {
        log.info("Inside UserServiceImpl in getUsers()");
        //database call
        List<User> listOfUser = this.userRepository.findAll();
        //Create UserDto type of list
        List<UserDto> listOfUserDto = new ArrayList<>();

        //Transfer value listOfUser to listOfUserDto
        for(User u : listOfUser)
        {
            //Create userDto type of Object
            UserDto userDto = new UserDto();
            //Student Entity to StudentDto
            BeanUtils.copyProperties(u,userDto);
            userDto.setAddress(u.getAddress());
            listOfUserDto.add(userDto);
        }
        log.info("Leaving UserServiceImpl in getUsers()");
        return listOfUserDto;
    }

    @Override
    public UserDto getUser(long userId) throws BusinessException {
        log.info("Inside UserServiceImpl in getUser()");

        //Database code
        User user_1 = this.userRepository.findByUserId(userId);

        if(user_1 == null)
        {
            throw new BusinessException(404,"Desired User is not found");
        }else{
            //Create UserDto type of Object
            UserDto userDto = new UserDto();
            //User Entity to UserDto Object
            BeanUtils.copyProperties(user_1, userDto);
            log.info("Leaving UserServiceImpl in getUser()");
            return userDto;
    }
}}
