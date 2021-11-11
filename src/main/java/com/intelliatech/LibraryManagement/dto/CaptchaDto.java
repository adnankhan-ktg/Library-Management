package com.intelliatech.LibraryManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaDto {

    private long captchaCodeId;
    private String captchaCode;

}
