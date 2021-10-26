package com.intelliatech.LibraryManagement.controller;

import com.intelliatech.LibraryManagement.dto.UserDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;
import com.intelliatech.LibraryManagement.repository.UserRepository;
import com.intelliatech.LibraryManagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController  {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;



    //create new user
    @PostMapping("/create")
    public ErrorMessage createUser(@RequestBody UserDto userDto) throws BusinessException
    {
        log.info(" Inside UserController in createUser() ");
          ErrorMessage errorMessage  =  this.userService.createUser(userDto);
          log.info("Leaving UserController in createUser()");
          return errorMessage;
    }
}
