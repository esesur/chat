package com.chat.config;

import com.chat.controller.HandshakeInterceptor;
import com.chat.controller.WebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    WebSocketHandler webSocketHandler;
    HandshakeInterceptor handshakeInterceptor;

    public WebSocketConfig(WebSocketHandler webSocketHandler, HandshakeInterceptor handshakeInterceptor) {
        this.webSocketHandler = webSocketHandler;
        this.handshakeInterceptor = handshakeInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/chat")
                .addInterceptors(handshakeInterceptor)
                .setAllowedOrigins("*");
    }

//    @Bean
//    public WebSocketHandler myHandler() {
//        return new WebSocketHandler();
//    }
}