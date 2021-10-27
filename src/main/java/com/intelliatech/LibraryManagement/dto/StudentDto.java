package com.intelliatech.LibraryManagement.dto;

import com.intelliatech.LibraryManagement.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String gender;
    private String dateOfBirth;
    private String registrationDate;
    private boolean isActive;
    private Address address;

}
