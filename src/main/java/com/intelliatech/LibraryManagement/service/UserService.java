package com.intelliatech.LibraryManagement.service;

import com.intelliatech.LibraryManagement.dto.UserDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;

public interface UserService {
    ErrorMessage createUser(UserDto userDto) throws BusinessException;
}
