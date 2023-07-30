package com.chat;

import com.chat.service.NetworkManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Main {
	private static NetworkManager networkManager;

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Main.class, args);
		networkManager = configurableApplicationContext.getBean(NetworkManager.class);
		if (args.length == 1) {
			networkManager.setHostIp(args[0]);
		}
	}
}
