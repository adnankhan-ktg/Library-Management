package com.intelliatech.LibraryManagement.serviceimpl;

import com.intelliatech.LibraryManagement.dto.UserDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ErrorMessage;
import com.intelliatech.LibraryManagement.model.User;
import com.intelliatech.LibraryManagement.repository.UserRepository;
import com.intelliatech.LibraryManagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public ErrorMessage createUser(UserDto userDto) throws BusinessException{
         log.info("Inside UserServiceImpl in createUser()");
          //UserEntity Object
        User user = new User();
        //UserDto to User Entity
        BeanUtils.copyProperties(userDto,user);
        user.setAddress(userDto.getAddress());
        //Make User username password in the encrypted form
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1 = this.userRepository.save(user);
        if(user1 != null)
        {
            log.info("leaving createUser() in UserServiceImpl");
           return new ErrorMessage("user successfully created",200);
        }else{
            log.info("throw exception");
           throw new BusinessException(400,"Bad Request");
        }

    }
}
