package com.intelliatech.LibraryManagement.service;

import com.intelliatech.LibraryManagement.dto.CaptchaDto;

public interface CaptchaService {
    public CaptchaDto getCaptcha() throws Exception;
    public boolean validateCaptcha(CaptchaDto captchaDto);
}
