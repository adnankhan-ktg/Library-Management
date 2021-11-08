package com.intelliatech.LibraryManagement.controller;

import com.intelliatech.LibraryManagement.dto.LoginDto;
import com.intelliatech.LibraryManagement.dto.TokenDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;
import com.intelliatech.LibraryManagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseMessage login(@RequestBody LoginDto loginDto) throws BusinessException
    {
        log.info("Inside UserLogin in login()");
        ResponseMessage responseMessage = this.userService.login(loginDto);
        log.info("Leaving UserLoginController in login()");
        return responseMessage;
    }

    @GetMapping("/login/generate/token")
    public TokenDto loginAndGenerateToken(@RequestBody LoginDto loginDto) throws BusinessException
    {
        log.info("Inside UserLoginController in loginAndGenerateToken()");
        TokenDto tokenDto = this.userService.generateToken(loginDto);
        log.info("Leaving UserLoginController in loginAndGenerateToken()");
        return tokenDto;
    }
}
