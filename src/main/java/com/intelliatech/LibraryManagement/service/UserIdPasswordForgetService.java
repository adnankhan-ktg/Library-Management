package com.intelliatech.LibraryManagement.service;

import com.intelliatech.LibraryManagement.controller.UserIdPasswordForgetController;
import com.intelliatech.LibraryManagement.dto.UserIdPasswordForgetDto;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;

public interface UserIdPasswordForgetService {

     ResponseMessage getPasswordForgetOtp(UserIdPasswordForgetDto userIdPasswordForgetDto)throws Exception;
     ResponseMessage validateOtp(UserIdPasswordForgetDto userIdPasswordForgetDto) throws Exception;
     ResponseMessage updatePassword(UserIdPasswordForgetDto userIdPasswordForgetDto) throws Exception;

}
