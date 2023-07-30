package com.chat.controller;

import com.chat.model.Message;
import com.chat.service.MessageService;
import com.chat.service.NetworkManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@RestController
public class MessageController {
    private MessageService messageService;
    private NetworkManager networkManager;

    public MessageController(MessageService messageService, NetworkManager networkManager) {
        this.messageService = messageService;
        this.networkManager = networkManager;
    }

    @GetMapping("/messages")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

    @GetMapping("/messages/clear")
    @ResponseStatus(value = HttpStatus.OK)
    public void clearMessages() {
        messageService.clearMessages();
    }

    @GetMapping("/address")
    @ResponseStatus(value = HttpStatus.OK)
    public String getServerAddress() throws UnknownHostException {
        if (networkManager.getHostIp() == null) {
            java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
            return localMachine.getHostAddress();
        } else {
            return networkManager.getHostIp();
        }
    }
}
