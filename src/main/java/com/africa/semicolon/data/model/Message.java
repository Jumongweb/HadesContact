package com.africa.semicolon.data.model;

import org.springframework.data.annotation.Id;

public class Message {
    @Id
    private String id;
    private String sender;
    private String receiver;

    private String title;
    private String body;
    private String dateSent;
}
