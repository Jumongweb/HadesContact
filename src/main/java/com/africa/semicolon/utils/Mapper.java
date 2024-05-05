package com.africa.semicolon.utils;

import com.africa.semicolon.data.model.Contact;
import com.africa.semicolon.data.model.User;
import com.africa.semicolon.dtos.request.CreateContactRequest;
import com.africa.semicolon.dtos.request.CreateUserRequest;
import com.africa.semicolon.dtos.request.LoginRequest;
import com.africa.semicolon.dtos.response.CreateUserResponse;
import com.africa.semicolon.dtos.response.LoginResponse;
import com.africa.semicolon.dtos.response.LogoutResponse;

public class Mapper {

    public static User mapCreateUserRequest(CreateUserRequest createUserRequest){
        User user = new User();
        user.setUsername(createUserRequest.getUsername().toLowerCase());
        user.setPassword(createUserRequest.getPassword());
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        return user;
    }

    public static CreateUserResponse mapCreateUserResponse(User user){
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setMessage("Registered Successfully");
        createUserResponse.setUser(user);
        return createUserResponse;
    }

    public static User mapLoginRequest(LoginRequest loginRequest){
        User user = new User();
        user.setUsername(loginRequest.getUsername().toLowerCase());
        user.setPassword(loginRequest.getPassword());
        return user;
    }

    public static LoginResponse mapLoginResponse(User user){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Logged In Successfully");
        return loginResponse;
    }
    public static LogoutResponse mapLogoutResponse(User user){
        LogoutResponse loginResponse = new LogoutResponse();
        loginResponse.setMessage("Logged Out Successfully");
        return loginResponse;
    }

    public static Contact mapCreateContact(CreateContactRequest createContactRequest){
        Contact newContact = new Contact();
        newContact.setFirstName(createContactRequest.getFirstName());
        newContact.setLastName(createContactRequest.getLastName());
        newContact.setNumber(createContactRequest.getNumber());
        newContact.setNote(createContactRequest.getNote());
        return newContact;
    }


}
