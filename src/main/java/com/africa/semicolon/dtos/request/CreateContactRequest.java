package com.africa.semicolon.dtos.request;

import com.africa.semicolon.data.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateContactRequest {
    private String username;
    private String number;
    private String firstName;
    private String lastName;
    private String note;

}
