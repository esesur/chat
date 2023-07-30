package com.chat.service;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SessionManager {
    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    public void addSession(WebSocketSession session) {
        if (!sessions.containsKey(session.getId())) {
            sessions.put(session.getId(), session);
        }
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session.getId());
    }

    public void broadcastMessage(TextMessage msg) throws IOException {
        for (WebSocketSession session : sessions.values()) {
            session.sendMessage(msg);
        }
    }
}
