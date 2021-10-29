package com.intelliatech.LibraryManagement.dto;

import com.intelliatech.LibraryManagement.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long userId;
    private String username;
    private String newPassword;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String gender;
    private String dateOfBirth;
    private String registrationDate;
    private int isActive;
    private Address address;



}
