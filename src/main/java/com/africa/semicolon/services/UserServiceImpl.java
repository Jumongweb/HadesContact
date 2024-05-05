package com.africa.semicolon.services;

import com.africa.semicolon.data.model.User;
import com.africa.semicolon.data.repository.UserRepository;
import com.africa.semicolon.dtos.request.CreateUserRequest;
import com.africa.semicolon.dtos.request.LoginRequest;
import com.africa.semicolon.dtos.request.LogoutRequest;
import com.africa.semicolon.dtos.response.LogoutResponse;
import com.africa.semicolon.exceptions.UserNotFoundException;
import com.africa.semicolon.dtos.response.CreateUserResponse;
import com.africa.semicolon.dtos.response.LoginResponse;
import com.africa.semicolon.exceptions.InvalidUsernameOrPasswordException;
import com.africa.semicolon.exceptions.UserAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.africa.semicolon.utils.Mapper.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        String username = createUserRequest.getUsername().toLowerCase();
        validate(username);
        User user = mapCreateUserRequest(createUserRequest);
        userRepository.save(user);
        return mapCreateUserResponse(user);
    }

    public void validate(String username){
        for (User user : userRepository.findAll()){
            if (user.getUsername().equals(username)){
                throw new UserAlreadyExist("User Already Exists");
            }
        }
    }
    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User does not exist");
        }
        return user;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = findUserByUsername(username);
        if (validLogin(username, password)){
            user.setLoggedIn(true);
            userRepository.save(user);
            return mapLoginResponse(user);
        }
        throw new InvalidUsernameOrPasswordException("Username or password is incorrect");
    }

    @Override
    public LogoutResponse logout(LogoutRequest logoutRequest) {
        String username = logoutRequest.getUsername().toLowerCase();
        User user = findUserByUsername(username);
        user.setLoggedIn(false);
        userRepository.save(user);
        return mapLogoutResponse(user);
    }

    public boolean validLogin(String username, String password){
        User user = findUserByUsername(username);
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }


}
