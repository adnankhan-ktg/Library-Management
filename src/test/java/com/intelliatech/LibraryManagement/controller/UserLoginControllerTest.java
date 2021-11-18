package com.intelliatech.LibraryManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelliatech.LibraryManagement.dto.CaptchaDto;
import com.intelliatech.LibraryManagement.dto.LoginDto;
import com.intelliatech.LibraryManagement.dto.TokenDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    //Make variable global
    //for token
    public static String token;

    //Global CaptchaDto Object
   public static CaptchaDto captchaDto = new CaptchaDto();


    @Test
    @Order(1)
    public void getCaptchaTest() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/captcha/get"))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        captchaDto = mapper.readValue(response, CaptchaDto.class);

    }

    @Test
    @Order(2)
    public void loginAndGenerateToken() throws Exception {
        LoginDto loginDto = new LoginDto
                ("8964882358", "adnan123",
                        captchaDto.getCaptchaCodeId(), captchaDto.getCaptchaCode());


        MvcResult result = this.mockMvc.perform(get("/login/user/generate/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(loginDto))).andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        TokenDto tokenDto = mapper.readValue(response, TokenDto.class);
        token = tokenDto.getToken();

    }


}
