package com.intelliatech.LibraryManagement.controller;

import com.intelliatech.LibraryManagement.dto.SubjectDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;
import com.intelliatech.LibraryManagement.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    private static final Logger log = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/create")
    public ResponseMessage createSubject(@RequestBody SubjectDto subjectDto) throws BusinessException
    {
        log.info("Inside SubjectController in createSubject()");
        ResponseMessage responseMessage = this.subjectService.createSubject(subjectDto);
        log.info("Leaving SubjectController in createSubject()");
        return responseMessage;
    }

    @GetMapping("/getSubjects")
    public List<SubjectDto> getSubjects()throws BusinessException
    {
        log.info("Inside SubjectController in getSubjects()");
        List<SubjectDto> listOfSubjectDto = this.subjectService.getSubjects();
        log.info("Leaving SubjectController in getSubjects()");
        return listOfSubjectDto;
    }

    @GetMapping("/getSubject/{subjectId}")
    public SubjectDto getSubject(@PathVariable("subjectId") long subjectId) throws BusinessException
    {
        log.info("Inside SubjectController in getSubject()");
        SubjectDto subjectDto = this.subjectService.getSubject(subjectId);
        log.info("Leaving SubjectController in getSubject()");
        return subjectDto;

    }

}
