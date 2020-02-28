package com.inbox.app;

import org.salespointframework.EnableSalespoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableSalespoint
@EnableScheduling
public class InboxChatAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InboxChatAppApplication.class, args);
	}

}
