package com.chat.service;

import org.springframework.stereotype.Component;

@Component
public class NetworkManager {
    private String hostIp;

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostIp() {
        return hostIp;
    }
}
