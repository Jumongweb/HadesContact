package com.africa.semicolon.data.repository;

import com.africa.semicolon.data.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String> {

}
