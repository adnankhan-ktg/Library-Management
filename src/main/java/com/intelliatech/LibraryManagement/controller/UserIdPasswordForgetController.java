package com.intelliatech.LibraryManagement.controller;


import com.intelliatech.LibraryManagement.dto.UserIdPasswordForgetDto;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;
import com.intelliatech.LibraryManagement.service.UserIdPasswordForgetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userphase")
public class UserIdPasswordForgetController {
    private static final Logger log = LoggerFactory.getLogger(UserIdPasswordForgetController.class);

    @Autowired
     private UserIdPasswordForgetService userIdPasswordForgetService;

    @GetMapping("/get/otp")
    public ResponseMessage getPasswordForgetOtp(@RequestBody UserIdPasswordForgetDto userIdPasswordForgetDto) throws Exception
    {
        log.info("Inside UserIdPasswordForgetController in getPasswordForgetOtp()");
        ResponseMessage responseMessage = this.userIdPasswordForgetService.getPasswordForgetOtp(userIdPasswordForgetDto);
        log.info("Leaving UserIdPasswordForgetController in getPasswordForgetOtp()");
        return responseMessage;

    }


    @GetMapping("/validate/otp")
    public ResponseMessage validateOtp(@RequestBody UserIdPasswordForgetDto userIdPasswordForgetDto) throws Exception{
        log.info("Inside UserIdPasswordForgetController in validateOtp()");
        ResponseMessage  responseMessage = this.userIdPasswordForgetService.validateOtp(userIdPasswordForgetDto);
        log.info("Leaving UserIdPasswordForgetController in validateOtp()");
        return responseMessage;
    }



    @PutMapping("/update/password")
    public ResponseMessage updatePassword(@RequestBody UserIdPasswordForgetDto userIdPasswordForgetDto) throws Exception
    {
        log.info("Inside UserIdPasswordForgetController in updatePassword()");
        ResponseMessage responseMessage = this.userIdPasswordForgetService.updatePassword(userIdPasswordForgetDto);
        log.info("Leaving UserIdPasswordForgetController in updatePassword()");
        return responseMessage;
    }


}
