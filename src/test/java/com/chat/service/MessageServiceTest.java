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
    UserService userService;

    @Autowired
    public MessageServiceTest(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @Test
    void messageServiceTest() {
        userService.addUser(new User("TestUser"));
        userService.addUser(new User("TestUser1"));
        User user = userService.getUserByName("TestUser");
        System.out.println(user.getId());
//        String messageText = "TestMessage";
//        Message message = new Message(1, messageText);
//        Message message1 = new Message(1, messageText);
//        messageService.addMessage(message);
//        messageService.addMessage(message1);
//        List<Message> expectedMessages = messageService.getMessages();
//        List<Message> actualMessages = messageService.getMessages();
//        assertEquals(expectedMessages.toString(), actualMessages.toString());
    }
}
