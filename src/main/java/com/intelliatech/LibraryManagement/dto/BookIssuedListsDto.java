package com.intelliatech.LibraryManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookIssuedListsDto {

    List<BookIssuedDto> listOfIssuedBook;
    List<BookIssuedDto> listOfReturnedBook;
}
