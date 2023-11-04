package com.chat.service;

import com.chat.model.Message;
import com.chat.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class MessageServiceTest {
    MessageService messageService;

    @Autowired
    public MessageServiceTest(MessageService messageService) {
        this.messageService = messageService;
    }

    @Test
    void messageServiceTest() {
        User user = new User("TestUser");
        String message = "TestMessage";
        messageService.addMessage(new Message(user, message));
        List<Message> expectedMessages = messageService.getMessages();
//        List<Message> actualMessages = messageService.getMessages();
//        assertEquals(expectedMessages.toString(), actualMessages.toString());
    }
}
