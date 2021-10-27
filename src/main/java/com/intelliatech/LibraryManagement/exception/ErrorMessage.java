package com.intelliatech.LibraryManagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private String Message;
    private Integer responseCode;


}
