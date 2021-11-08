package com.intelliatech.LibraryManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomException {

    @ExceptionHandler(value = BusinessException.class)
     public ResponseEntity<ResponseMessage> handleRecordNotFoundException(BusinessException e)
     {
         System.out.println(e);
         return new ResponseEntity<>(new ResponseMessage(e.getMessage(),e.getCode()),HttpStatus.valueOf(200));
     }
}
