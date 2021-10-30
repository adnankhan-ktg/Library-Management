package com.intelliatech.LibraryManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailRequestDto {

        private String receiverAddress;
        private String message;
        private String subject;

    }
