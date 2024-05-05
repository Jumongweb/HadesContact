package com.africa.semicolon.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("Contacts")
public class Contact {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String number;
    private User user;
    private String note;
    private LocalDateTime dateCreated = LocalDateTime.now();
}
