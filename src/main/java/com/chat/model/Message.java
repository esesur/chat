package com.chat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("messages")
public class Message {
    @Id
    private long id;
    private final long timeSent;
    private final long sender;
    private String content;

    public Message(long sender, String content) {
        timeSent = System.currentTimeMillis();
        this.sender = sender;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getSenderId() {
        return sender;
    }

    public long getTimeSent() {
        return timeSent;
    }

//    public void setTimeSent(long timeSent) {
//        this.timeSent = timeSent;
//    }
}
