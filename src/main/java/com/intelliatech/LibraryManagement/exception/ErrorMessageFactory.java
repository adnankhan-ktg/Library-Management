package com.intelliatech.LibraryManagement.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessageFactory {
	
	private ErrorMessageFactory() {}

    static Map<Integer, String> messages = new HashMap<>();
    static Map<String, Integer> exceptions = new HashMap<>();

    public static String getErrorMessage(Integer errorCode) {
        return messages.get(errorCode);
    }

    public static Integer getErrorCode(String exception) {
        return exceptions.get(exception);
    }
}
