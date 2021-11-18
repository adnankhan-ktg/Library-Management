package com.intelliatech.LibraryManagement.controller;

import com.intelliatech.LibraryManagement.dto.CaptchaDto;
import com.intelliatech.LibraryManagement.dto.LoginDto;
import com.intelliatech.LibraryManagement.dto.TokenDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;
import com.intelliatech.LibraryManagement.service.CaptchaService;
import com.intelliatech.LibraryManagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserLoginController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CaptchaService captchaService;


    @GetMapping("/user/generate/token")
    public TokenDto loginAndGenerateToken(@RequestBody LoginDto loginDto) throws BusinessException
    {
        log.info("Inside UserLoginController in loginAndGenerateToken()");
        //Check Captcha validation
        //Call Captcha Service
        boolean status = this.captchaService.validateCaptcha(new CaptchaDto(loginDto.getCaptchaCodeId(),loginDto.getCaptchaCode()));
        if(status == false)
        {
            throw new BusinessException(406,"Captcha not acceptable");
        }
        TokenDto tokenDto = this.userService.generateToken(loginDto);
        log.info("Leaving UserLoginController in loginAndGenerateToken()");
        return tokenDto;
    }
}
