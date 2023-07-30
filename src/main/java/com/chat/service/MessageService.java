package com.chat.service;

import com.chat.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final List<Message> messages = new ArrayList<>();

    public void addMessage(String messageText) {
        Message message = new Message();
        message.setContent(messageText);
        messages.add(message);
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    public void clearMessages() {
        messages.clear();
    }
}
