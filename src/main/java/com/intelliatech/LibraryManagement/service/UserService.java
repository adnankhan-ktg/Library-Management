package com.intelliatech.LibraryManagement.service;

import com.intelliatech.LibraryManagement.dto.LoginDto;
import com.intelliatech.LibraryManagement.dto.TokenDto;
import com.intelliatech.LibraryManagement.dto.UserDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;

import java.util.List;

public interface UserService {
    ResponseMessage createUser(UserDto userDto) throws Exception;
    ResponseMessage login(LoginDto loginDto) throws BusinessException;
    List<UserDto> getUsers() throws BusinessException;
    UserDto getUser(long userId) throws BusinessException;
    TokenDto generateToken(LoginDto loginDto) throws BusinessException;
}
