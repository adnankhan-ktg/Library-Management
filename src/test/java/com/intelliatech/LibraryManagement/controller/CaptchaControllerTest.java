package com.intelliatech.LibraryManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelliatech.LibraryManagement.dto.CaptchaDto;
import com.intelliatech.LibraryManagement.service.CaptchaService;
import com.intelliatech.LibraryManagement.service.SubjectService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CaptchaControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;


    //For create fake service
    @MockBean
    private CaptchaService captchaService;

    @Test
    @Order(1)
    public void getCaptchaTest() throws Exception {

        //Call Fake service..
        when(captchaService.getCaptcha()).thenReturn(new CaptchaDto(13,"q1tG4W"));

        //Call the  API,Real Service and Database as well
        MvcResult result = this.mockMvc.perform(get("/captcha/get"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.captchaCodeId").value(13))
                .andExpect(MockMvcResultMatchers.jsonPath("$.captchaCode").value("q1tG4W"))
                .andReturn();


    }


}