package com.chat.model;

import java.time.LocalDate;

public class Message {
    private long timestamp;
    private final User sender;
    private String content;

    public Message(User sender, String content) {
        timestamp = System.currentTimeMillis();
        this.sender = sender;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
