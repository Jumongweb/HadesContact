package com.africa.semicolon.services;

import com.africa.semicolon.data.model.Contact;
import com.africa.semicolon.data.repository.ContactRepository;
import com.africa.semicolon.dtos.request.CreateContactRequest;
import com.africa.semicolon.dtos.response.CreateContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.africa.semicolon.utils.Mapper.mapCreateContact;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public CreateContactResponse createContact(CreateContactRequest createContactRequest) {
        Contact contact = mapCreateContact(createContactRequest);
        contact.setUser();
        return null;
    }

    @Override
    public Long count() {
        return contactRepository.count();
    }
}

