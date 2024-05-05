package com.africa.semicolon.services;

import com.africa.semicolon.data.model.User;
import com.africa.semicolon.dtos.request.CreateUserRequest;
import com.africa.semicolon.dtos.request.LoginRequest;
import com.africa.semicolon.dtos.request.LogoutRequest;
import com.africa.semicolon.dtos.response.CreateUserResponse;
import com.africa.semicolon.dtos.response.LoginResponse;
import com.africa.semicolon.dtos.response.LogoutResponse;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    long count();
    User findUserByUsername(String username);
    LoginResponse login(LoginRequest loginRequest);
    LogoutResponse logout(LogoutRequest logoutRequest);
}
