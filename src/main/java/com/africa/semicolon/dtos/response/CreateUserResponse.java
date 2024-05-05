package com.africa.semicolon.dtos.response;

import com.africa.semicolon.data.model.User;
import lombok.Data;

@Data
public class CreateUserResponse {
    private String message;
    private User user;
}
