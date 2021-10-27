package com.intelliatech.LibraryManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    private String username;
    private String mobileNumber;
    private String email;
    private String password;
}
