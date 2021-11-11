package com.intelliatech.LibraryManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Captcha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "captcha_code_id")
    private long captchaCodeId;
    @Column(name = "captcha_code")
    private String captchaCode;
}
