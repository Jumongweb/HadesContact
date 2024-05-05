package com.africa.semicolon.dtos.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
