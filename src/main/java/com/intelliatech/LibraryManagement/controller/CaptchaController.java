package com.intelliatech.LibraryManagement.controller;

import com.intelliatech.LibraryManagement.constants.Constants;
import com.intelliatech.LibraryManagement.dto.CaptchaDto;
import com.intelliatech.LibraryManagement.service.CaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.CAPTCHA)
public class CaptchaController {

    private static final Logger log = LoggerFactory.getLogger(CaptchaController.class);

    @Autowired
    private CaptchaService captchaService;

    @GetMapping("/get")
    public CaptchaDto getCaptcha() throws Exception
    {
        log.info("Inside CaptchaController in getCaptcha()");
        CaptchaDto  captchaDto = this.captchaService.getCaptcha();
        log.info("Leaving CaptchaController in getCaptcha()");
        return captchaDto;

    }

}
