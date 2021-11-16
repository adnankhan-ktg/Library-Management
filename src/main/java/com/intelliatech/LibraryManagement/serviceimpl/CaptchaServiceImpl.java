package com.intelliatech.LibraryManagement.serviceimpl;

import com.intelliatech.LibraryManagement.constants.Constants;
import com.intelliatech.LibraryManagement.dto.CaptchaDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.model.Captcha;
import com.intelliatech.LibraryManagement.repository.CaptchaRepository;
import com.intelliatech.LibraryManagement.service.CaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    private static final Logger log = LoggerFactory.getLogger(CaptchaServiceImpl.class);

    @Autowired
    private CaptchaRepository captchaRepository;

    @Override
    public CaptchaDto getCaptcha() throws BusinessException {
        log.info("Inside CaptchaServiceImpl in getCaptcha()");
        //Captcha pattern
        String pattern = Constants.CAPTCHA_PATTERN_STRING;
        //Create Random Class Object
        Random rnd = new Random();
        //Create StringBuilder Object
        StringBuilder sb = new StringBuilder();
        //Generate Captcha through the logic
        for (int i = 0; i < 7; i++) {
            sb.append(pattern.charAt(rnd.nextInt(pattern.length())));
        }
        //Create Captcha Class Object
        Captcha captcha = new Captcha();
        //Set CaptchaCode in the Captcha object
        captcha.setCaptchaCode(sb.toString());
        //DataBase Call
        Captcha captcha1 = this.captchaRepository.save(captcha);
        //Check Captcha save or not in the database
        if(captcha1 == null)
        {
            //throw captcha not persist exception
            throw new BusinessException(500,"Internal Server Error");
        }

        //Create CaptchaDto Object
        CaptchaDto captchaDto = new CaptchaDto();
        //Copy Captcha entity value to CaptchaDto
        BeanUtils.copyProperties(captcha1,captchaDto);
        log.info("Leaving CaptchaServiceImpl in getCaptcha()");
        return captchaDto;
    }

    @Override
    public boolean validateCaptcha(CaptchaDto captchaDto) {
        log.info("Inside CaptchaServiceImpl in validateCaptcha()");
        //DataBase Call
        //Get Captcha via CaptchaCodeId
        Captcha captcha = this.captchaRepository.findByCaptchaCodeId(captchaDto.getCaptchaCodeId());
        //Check CaptchaCode before or not
        if(captcha != null)
        {
            //Check CaptchaCode same or not before and current
            if(captchaDto.getCaptchaCode().equals(captcha.getCaptchaCode()))
            {
                //Database Call
                //Delete the captcha record
                this.captchaRepository.delete(captcha);
                log.info("Leaving CaptchaServiceImpl in validateCaptcha()");
                return true;
            }else{
                log.info("Leaving CaptchaServiceImpl in validateCaptcha()");
                return false;
            }
        }else{
            log.info("Leaving CaptchaServiceImpl in validateCaptcha()");
            return false;
        }
    }
}
