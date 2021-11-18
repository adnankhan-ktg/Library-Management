package com.intelliatech.LibraryManagement.serviceimpl;

import com.intelliatech.LibraryManagement.config.JwtUserDetailsService;
import com.intelliatech.LibraryManagement.config.JwtUtil;
import com.intelliatech.LibraryManagement.constants.Constants;
import com.intelliatech.LibraryManagement.dto.*;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;
import com.intelliatech.LibraryManagement.model.User;
import com.intelliatech.LibraryManagement.repository.UserRepository;
import com.intelliatech.LibraryManagement.service.UserService;
import com.intelliatech.LibraryManagement.service.helper.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
       private JwtUtil jwtTokenUtil;
    @Autowired
       private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private MailService mailService;

    @Override
    public ResponseMessage createUser(UserDto userDto) throws Exception{
         log.info("Inside UserServiceImpl in createUser()");

          //Check User already exists or not
          User user_2 = this.userRepository.findByUsernameOrEmailOrMobileNumber(userDto.getUsername(),userDto.getEmail(),userDto.getMobileNumber());
          if(user_2 != null)
          {
              throw new BusinessException(208, Constants.USER_ALREADY_EXISTS);
          }
          //Compare new password and confirm password
        if((userDto.getNewPassword().equals(userDto.getConfirmPassword())) == false)
           {
               throw new BusinessException(406,Constants.PASSWORD_NOT_MATCH);
           }
        //UserEntity Object
        User user = new User();
        //UserDto to User Entity
        BeanUtils.copyProperties(userDto,user);
        user.setAddress(userDto.getAddress());
        user.setPassword(userDto.getConfirmPassword());
        Date date1=new SimpleDateFormat(Constants.DATE_FORMAT_DD_MM_YYYY).parse(userDto.getDateOfBirth());
        user.setDateOfBirth(date1);
        user.setRegistrationDate(new Date());
        user.setIsActive(1);
        user.setRole("ROLE_"+user.getRole());
        //Make User username password in the encrypted form
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user_1 = this.userRepository.save(user);
        if(user_1 != null)
        {
            log.info("Sent registration mail to the user");
            //Send account signup notification to the user
//            mailService.sendMail(new MailRequestDto(user_1.getEmail(),user_1.getFirstName()+" "+user_1.getLastName()+" Your library User account successfully created","Account Registration"));
            log.info("leaving createUser() in UserServiceImpl");
           return new ResponseMessage(Constants.USER_SUCCESSFULLY_CREATED,200);
        }else{
            log.info("throw exception");
           throw new BusinessException(400,Constants.BAD_REQUEST);
        }

    }

    @Override
    public ResponseMessage login(LoginDto loginDto) throws  BusinessException{
        log.info("Inside UserServiceImpl in login()");
        User user_1 = this.userRepository.findByUsernameOrEmailOrMobileNumber(loginDto.getUsername(),loginDto.getUsername(),loginDto.getUsername());
        if(user_1 != null)
        {
            if(loginDto.getPassword().equals(user_1.getPassword()))
            {
                log.info("leaving UserServiceImpl in login()");
                return new ResponseMessage(Constants.USER_SUCCESSFULLY_LOGIN,200);
            }else{
                log.info("Throw exception username exists but password not match");
                throw new BusinessException(406,Constants.PASSWORD_NOT_MATCH);
            }
        }else{
            log.info("Throw exception username doesn't exists");
            throw new BusinessException(404,Constants.USER_NOT_FOUND);
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
            throw new BusinessException(404,Constants.USER_NOT_FOUND);
        }else{
            //Create UserDto type of Object
            UserDto userDto = new UserDto();
            //User Entity to UserDto Object
            BeanUtils.copyProperties(user_1, userDto);
            log.info("Leaving UserServiceImpl in getUser()");
            return userDto;
    }
}


    @Override
    public TokenDto generateToken(LoginDto loginDto) throws BusinessException {
        log.info("Inside UserServiceImpl in generateToken()");
        User user = this.userRepository.findByUsernameOrEmailOrMobileNumber(loginDto.getUsername(),loginDto.getUsername(),loginDto.getUsername());
        if(user != null)
        {

            if(passwordEncoder.matches(loginDto.getPassword(),user.getPassword()))
            {
                UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(loginDto.getUsername());
                String token = this.jwtTokenUtil.generateToken(userDetails);
//                log.info(" Login notification sent to the user");
//                mailService.sendMail(new MailRequestDto(user.getEmail(),user.getFirstName()+" "+user.getLastName()+" You had login the account just","Login Account"));

                log.info("Generate token ");
                log.info("Leaving UserServiceImpl in generateToken()");
                return new TokenDto(token);
            }{
                log.error("Throw Password doesn't match Exception");
                throw new BusinessException(406,Constants.PASSWORD_NOT_MATCH);
        }
        }
        else{
            log.error("Throw User not found Exception");
            throw new BusinessException(404,Constants.USER_NOT_FOUND);
        }

    }
}
