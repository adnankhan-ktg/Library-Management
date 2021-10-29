package com.intelliatech.LibraryManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentBookReturnedDto {

    private long studentId;
    private long bookId;
}
