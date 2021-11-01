package com.intelliatech.LibraryManagement.dto;

import com.intelliatech.LibraryManagement.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoNew {
    private String firstName;
//    private String lastName;
    private String email;
    private String mobileNumber;
    private String gender;
//    private String dateOfBirth;

}
