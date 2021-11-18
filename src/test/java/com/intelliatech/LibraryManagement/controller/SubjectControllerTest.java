package com.intelliatech.LibraryManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelliatech.LibraryManagement.constants.Constants;
import com.intelliatech.LibraryManagement.dto.CaptchaDto;
import com.intelliatech.LibraryManagement.dto.LoginDto;
import com.intelliatech.LibraryManagement.dto.SubjectDto;
import com.intelliatech.LibraryManagement.dto.TokenDto;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;
import com.intelliatech.LibraryManagement.service.SubjectService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubjectControllerTest {

//Common code.............................................................
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    //Make variable global
    //for token
    public static String token;

    //Global CaptchaDto Object
    public static CaptchaDto captchaDto = new CaptchaDto();
    //Common code end.......................................................................

    //For create fake service
    @MockBean
    private SubjectService subjectService;


    //Common code --> for get the JWT token...................................................
    @Test
    @Order(1)
    public void getCaptchaTest() throws Exception {
        //Call the  API,Real Service and Database as well
        MvcResult result = this.mockMvc.perform(get("/captcha/get"))
                .andExpect(status().isOk()).andReturn();

        //Get the Json String response
        String response = result.getResponse().getContentAsString();
        //Convert Json String into the Object basically
        captchaDto = mapper.readValue(response, CaptchaDto.class);

    }

    @Test
    @Order(2)
    public void loginAndGenerateToken() throws Exception {
        //Create LoginDto for login and get the 'JWT TOKEN'
        LoginDto loginDto = new LoginDto
                ("8964882358", "adnan123",
                        captchaDto.getCaptchaCodeId(), captchaDto.getCaptchaCode());


        //API call along with request data and get the response basically JWT TOKEN HERE
        //Here the real database hitting and generate the token
        MvcResult result = this.mockMvc.perform(get("/login/user/generate/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(loginDto))).andExpect(status().isOk()).andReturn();

        //Get the response and change into the string basically-->response
        String response = result.getResponse().getContentAsString();
        //Change Json String into Object
        TokenDto tokenDto = mapper.readValue(response, TokenDto.class);
        //Store the token into the global token variable
        token = "Bearer "+tokenDto.getToken();

    }
    //End the common code--> for JWT TOKEN.................................................................


    @Test
    @Order(3)
    void createSubject() throws Exception{

        //Create SubjectDto
        SubjectDto subjectDto = new SubjectDto(1,"physics",100);
        //Fake service request and  response
           when(subjectService.createSubject(subjectDto)).thenReturn(new ResponseMessage(Constants.SUBJECT_SUCCESSFULLY_CREATED,200));

           //Call Controller and get the response
           mockMvc.perform(post("/subject/create")
                   .header(HttpHeaders.AUTHORIZATION,token)
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(mapper.writeValueAsString(subjectDto)))
                   .andExpect(status().isOk())
                   .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                           .value("Subject Successfully Created"))
                   .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode").value(200)).andReturn();
    }

    @Test
    @Order(4)
    void getSubjects() throws Exception {

        //Create list of SubjectDto type
        List<SubjectDto> listOfSubjectDto = new ArrayList<>();
        listOfSubjectDto.add(new SubjectDto(1,"physics",100));
        listOfSubjectDto.add(new SubjectDto(2,"mathematics",101));
        listOfSubjectDto.add(new SubjectDto(3,"chemistry",102));

        //Call the Controller and get the response
        when(subjectService.getSubjects()).thenReturn(listOfSubjectDto);
       MvcResult mvcResult = mockMvc.perform(get("/subject/getSubjects")
                .header(HttpHeaders.AUTHORIZATION,token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
       String jsonStringArray = mvcResult.getResponse().getContentAsString();
        List<SubjectDto> list = Arrays.asList(mapper.readValue(jsonStringArray,SubjectDto[].class));
        Assertions.assertEquals(3,list.size());



    }

    @Test
    @Order(5)
    void getSubject()throws Exception {

        //Create multiple subject like table
        Map<Integer,SubjectDto> listOfSubjectDto = new HashMap<>();
        listOfSubjectDto.put(1,new SubjectDto(1,"physics",100));
        listOfSubjectDto.put(2,new SubjectDto(2,"mathematics",101));
        listOfSubjectDto.put(3,new SubjectDto(3,"chemistry",102));


        //For Dynamic value of subject Id
        int subjectId = 1;


        //For Fake service request and response
        when(subjectService.getSubject(subjectId)).thenReturn(listOfSubjectDto.get(subjectId));


        //Call Controller along with request body,path variable etc. and get the response
        MvcResult result = mockMvc.perform(get("/subject/getSubject/"+subjectId)
                .header(HttpHeaders.AUTHORIZATION,token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectId")
                        .value(listOfSubjectDto.get(subjectId).getSubjectId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectName").value(listOfSubjectDto.get(subjectId).getSubjectName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectCode").value(listOfSubjectDto.get(subjectId).getSubjectCode())).andReturn();


    }
}