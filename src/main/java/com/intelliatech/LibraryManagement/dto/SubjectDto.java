package com.intelliatech.LibraryManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private long subjectId;
    private String subjectName;
    private long subjectCode;
}
