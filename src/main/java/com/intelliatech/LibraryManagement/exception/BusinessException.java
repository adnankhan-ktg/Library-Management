package com.intelliatech.LibraryManagement.exception;

public class BusinessException extends Exception{

     private int code;

    public BusinessException() {
        super();
    }

    public BusinessException(int code , String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
