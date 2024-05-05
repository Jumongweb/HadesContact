package com.africa.semicolon.dtos.request;

import com.africa.semicolon.exceptions.HadesContactException;

public class UserNotFoundException extends HadesContactException {
    public UserNotFoundException(String message){
        super(message);
    }
}
