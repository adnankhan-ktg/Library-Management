package com.intelliatech.LibraryManagement.controller;

import com.intelliatech.LibraryManagement.constants.Constants;
import com.intelliatech.LibraryManagement.dto.UserDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;
import com.intelliatech.LibraryManagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.USER)
public class UserController  {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;



    //create new user
    @PostMapping("/create")
    public ResponseMessage createUser(@RequestBody UserDto userDto) throws Exception
    {
        log.info(" Inside UserController in createUser() ");
          ResponseMessage responseMessage =  this.userService.createUser(userDto);
          log.info("Leaving UserController in createUser()");
          return responseMessage;
    }

    @GetMapping("/getUsers")
    public List<UserDto> getUsers() throws BusinessException
    {
        log.info("Inside UserController in getUsers()");
        List<UserDto> listOfUser = this.userService.getUsers();
        log.info("Leaving UserController in getUsers()");
        return listOfUser;
    }


    @GetMapping("/getUser/{userId}")
    public UserDto getUser(@PathVariable("userId") long userId) throws BusinessException
    {
        log.info("Inside UserController in getUsers()");
        UserDto userDto = this.userService.getUser(userId);
        log.info("Leaving UserController in getUsers()");
        return userDto;

    }


}
