package com.chat.service;

import com.chat.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SessionManager {
    private final Map<WebSocketSession, User> sessions = new HashMap<>();

    public void addSession(WebSocketSession session, User user) {
        if (!sessions.containsKey(session)) {
            sessions.put(session, user);
        }
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    public void broadcastMessage(TextMessage msg) throws IOException {
        for (WebSocketSession session : sessions.keySet()) {
            session.sendMessage(msg);
        }
    }
}
