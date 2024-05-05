package com.africa.semicolon.services;

import com.africa.semicolon.dtos.request.CreateContactRequest;
import com.africa.semicolon.dtos.response.CreateContactResponse;

public interface ContactService {
    CreateContactResponse createContact(CreateContactRequest createContactRequest);
    Long count();
}
