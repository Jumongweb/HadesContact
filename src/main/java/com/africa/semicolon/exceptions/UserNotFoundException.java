package com.africa.semicolon.exceptions;

import com.africa.semicolon.exceptions.HadesContactException;

public class UserNotFoundException extends HadesContactException {
    public UserNotFoundException(String message){
        super(message);
    }
}
