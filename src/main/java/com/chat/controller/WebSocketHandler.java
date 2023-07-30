package com.chat.controller;

import com.chat.service.MessageService;
import com.chat.service.SessionManager;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    MessageService messageService;
    SessionManager sessionManager;

    public WebSocketHandler(MessageService messageService, SessionManager sessionManager) {
        this.messageService = messageService;
        this.sessionManager = sessionManager;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionManager.addSession(session);
        System.out.println("New connection : " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionManager.removeSession(session);
        System.out.println("Disconnect : " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        sessionManager.addSession(session);
        String messageText = message.getPayload();
        messageService.addMessage(messageText);
        System.out.println(session.getId() + " : " + messageText);
        sessionManager.broadcastMessage(message);
    }
}
