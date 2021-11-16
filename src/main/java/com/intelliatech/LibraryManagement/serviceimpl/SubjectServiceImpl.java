package com.intelliatech.LibraryManagement.serviceimpl;

import com.intelliatech.LibraryManagement.constants.Constants;
import com.intelliatech.LibraryManagement.dto.SubjectDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;
import com.intelliatech.LibraryManagement.model.Subject;
import com.intelliatech.LibraryManagement.repository.SubjectRepository;
import com.intelliatech.LibraryManagement.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private static final Logger log = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public ResponseMessage createSubject(SubjectDto subjectDto) throws BusinessException{
           log.info("Inside SubjectServiceImpl in createSubject()");
           //find subject entity by subject code
        Subject subject_1 = this.subjectRepository.findBySubjectCode(subjectDto.getSubjectCode());
        if(subject_1 != null)
        {
            log.info("throw exception");
            throw new BusinessException(208,Constants.SUBJECT_ALREADY_EXISTS);
        }
        //Create Subject Entity type of Object
        Subject subject_2 = new Subject();
        //SubjectDto to Subject Entity value copy
        BeanUtils.copyProperties(subjectDto,subject_2);
        //Database call
        Subject subject_3 = this.subjectRepository.save(subject_2);
        if(subject_3 == null)
        {
            log.info("Throw exception");
            throw new BusinessException(400,"Bad Request");
        }else{
            log.info("Subject Successfully created");
            return new ResponseMessage(Constants.SUBJECT_SUCCESSFULLY_CREATED,200);
        }


    }

    @Override
    public List<SubjectDto> getSubjects() {
        log.info("Inside SubjectServiceImpl getSubjects()");
        //database call
        List<Subject> listOfSubject = this.subjectRepository.findAll();
        //Create list of subject type of SubjectDto
        List<SubjectDto> listOfSubjectDto = new ArrayList<>();

        for(Subject s : listOfSubject)
        {
            //Create SubjectDto type of Object
            SubjectDto subjectDto = new SubjectDto();
            //Subject Entity to SubjectDto
            BeanUtils.copyProperties(s,subjectDto);
            //Add SubjectDto into the list
            listOfSubjectDto.add(subjectDto);
        }

        log.info("Leaving SubjectServiceImpl in getSubjects()");
        return listOfSubjectDto;

    }

    @Override
    public SubjectDto getSubject(long subjectId) throws BusinessException{
        log.info("Inside SubjectServiceImpl in getSubject()");
        //Database call
        Subject subject = this.subjectRepository.findBySubjectId(subjectId);
        if(subject == null)
        {
            log.info("throw exception");
            throw new BusinessException(404, Constants.SUBJECT_NOT_FOUND);
        }
        //SubjectDto type of Object create
        SubjectDto subjectDto = new SubjectDto();
        //Subject entity to SubjectDto
        BeanUtils.copyProperties(subject,subjectDto);
        log.info("Leaving SubjectServiceImpl in getSubject()");
        return subjectDto;

    }
}
