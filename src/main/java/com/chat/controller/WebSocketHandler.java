package com.chat.controller;

import com.chat.model.Message;
import com.chat.model.User;
import com.chat.service.MessageService;
import com.chat.service.SessionManager;
import com.chat.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    MessageService messageService;
    SessionManager sessionManager;
    UserService userService;

    public WebSocketHandler(MessageService messageService, SessionManager sessionManager, UserService userService) {
        this.messageService = messageService;
        this.sessionManager = sessionManager;
        this.userService = userService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        User user = new User("DefaultUser");
        userService.addUser(user);
        sessionManager.addSession(session, user);
        System.out.println("New connection : " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionManager.removeSession(session);
        System.out.println("Disconnect : " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        userManager.addSession(session);
        String messageText = message.getPayload();
        User user = new User("DefaultUser");
        messageService.addMessage(new Message(user.getId(), messageText)); // TODO
        System.out.println(session.getId() + " : " + messageText);
        sessionManager.broadcastMessage(message);
    }
}
