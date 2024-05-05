package com.africa.semicolon.services;

import com.africa.semicolon.dtos.request.CreateContactRequest;
import com.africa.semicolon.dtos.request.CreateUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ContactServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Test
    public void testThatContactServiceCanCreateContactIfUserIsValid(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("username");
        createUserRequest.setPassword("password");
        createUserRequest.setFirstName("firstName");
        createUserRequest.setLastName("lastName");
        userService.createUser(createUserRequest);

        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setUsername("username");
        createContactRequest.setNumber("08031323334");
        createContactRequest.setFirstName("Jane");
        createContactRequest.setLastName("Doe");
        createContactRequest.setNote("One night stand");
        contactService.createContact(createContactRequest);
        assertEquals(1, contactService.count());
    }

}