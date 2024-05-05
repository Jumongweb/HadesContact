package com.africa.semicolon.data.model;

import com.africa.semicolon.data.repository.UserRepository;
import com.africa.semicolon.dtos.request.CreateUserRequest;
import com.africa.semicolon.dtos.request.LoginRequest;
import com.africa.semicolon.dtos.request.LogoutRequest;
import com.africa.semicolon.exceptions.UserNotFoundException;
import com.africa.semicolon.exceptions.InvalidUsernameOrPasswordException;
import com.africa.semicolon.exceptions.UserAlreadyExist;
import com.africa.semicolon.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
    }

    @Test
    public void testThatUserServiceCanCreateUser(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("John");
        createUserRequest.setLastName("Doe");
        createUserRequest.setUsername("username");
        createUserRequest.setPassword("password");
        userService.createUser(createUserRequest);
        assertEquals(1, userService.count());
    }

    @Test
    public void testThatUserServiceCanCreateMoreThanOneUser(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("John");
        createUserRequest.setLastName("Doe");
        createUserRequest.setUsername("username");
        createUserRequest.setPassword("password");
        userService.createUser(createUserRequest);

        CreateUserRequest createUserRequest2 = new CreateUserRequest();
        createUserRequest2.setFirstName("Aaron");
        createUserRequest2.setLastName("Dean");
        createUserRequest2.setUsername("username2");
        createUserRequest2.setPassword("password");
        userService.createUser(createUserRequest2);
        assertEquals(2, userService.count());
    }

    @Test
    public void testThatUserServiceThrowsExceptionIfUserWithSameUsernameRegister(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("John");
        createUserRequest.setLastName("Doe");
        createUserRequest.setUsername("username");
        createUserRequest.setPassword("password");
        userService.createUser(createUserRequest);

        CreateUserRequest createUserRequest2 = new CreateUserRequest();
        createUserRequest2.setFirstName("Aaron");
        createUserRequest2.setLastName("Dean");
        createUserRequest2.setUsername("username");
        createUserRequest2.setPassword("password");
        assertThrows(UserAlreadyExist.class, () -> userService.createUser(createUserRequest2));
    }

    @Test
    public void testThatUserServiceValidateUserByLetter(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("John");
        createUserRequest.setLastName("Doe");
        createUserRequest.setUsername("username");
        createUserRequest.setPassword("password");
        userService.createUser(createUserRequest);

        CreateUserRequest createUserRequest2 = new CreateUserRequest();
        createUserRequest2.setFirstName("Aaron");
        createUserRequest2.setLastName("Dean");
        createUserRequest2.setUsername("USERname");
        createUserRequest2.setPassword("password");
        assertThrows(UserAlreadyExist.class, () -> userService.createUser(createUserRequest2));
    }

    @Test
    public void testThatUserServiceCanFinderUser(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("username");
        createUserRequest.setPassword("password");
        createUserRequest.setFirstName("John");
        createUserRequest.setLastName("Carter");
        userService.createUser(createUserRequest);
        assertEquals(1, userService.count());
        assertEquals("John", userService.findUserByUsername("username").getFirstName());
    }

    @Test
    public void testThatUserServiceThrowExceptionIfUserThatDoesNotExistIsSearchedFor(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("username");
        createUserRequest.setPassword("password");
        createUserRequest.setFirstName("John");
        createUserRequest.setLastName("Carter");
        userService.createUser(createUserRequest);
        assertEquals(1, userService.count());
        assertThrows(UserNotFoundException.class, ()->userService.findUserByUsername("wrongUsername"));
    }

    @Test
    public void testThatUserCanLogin() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("John");
        createUserRequest.setLastName("Doe");
        createUserRequest.setUsername("username");
        createUserRequest.setPassword("password");
        userService.createUser(createUserRequest);
        User foundUser = userService.findUserByUsername("username");
        assertFalse(foundUser.isLoggedIn());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        userService.login(loginRequest);
        assertEquals(1, userService.count());
        foundUser = userService.findUserByUsername("username");
        assertTrue(foundUser.isLoggedIn());
    }

    @Test
    public void testThatUserServiceThrowsExceptionIfUserThatDoesNotExistTryToLogin() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("wrongUsername");
        loginRequest.setPassword("password");
        assertThrows(UserNotFoundException.class, ()->userService.login(loginRequest));
    }

    @Test
    public void testThatUserServiceCannotLoginUserWithWrongPassword() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("username");
        createUserRequest.setPassword("password");
        createUserRequest.setFirstName("firstName");
        createUserRequest.setLastName("lastName");
        userService.createUser(createUserRequest);
        assertEquals(1, userService.count());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("wrongPassword");
        assertThrows(InvalidUsernameOrPasswordException.class, ()->userService.login(loginRequest));
    }

    @Test
    public void testThatUserCanLogout(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("username");
        createUserRequest.setPassword("password");
        createUserRequest.setFirstName("firstName");
        createUserRequest.setLastName("lastName");
        userService.createUser(createUserRequest);
        assertEquals(1, userService.count());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        userService.login(loginRequest);
        User foundUser = userService.findUserByUsername(loginRequest.getUsername());
        assertTrue(foundUser.isLoggedIn());

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("username");
        userService.logout(logoutRequest);
        foundUser = userService.findUserByUsername(loginRequest.getUsername());
        assertFalse(foundUser.isLoggedIn());
    }

}