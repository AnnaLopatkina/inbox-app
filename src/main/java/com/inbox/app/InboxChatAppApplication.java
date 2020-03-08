package com.inbox.app;

import java.io.File;

import org.salespointframework.EnableSalespoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.inbox.app.controller.FileUploadController;

@SpringBootApplication
@EnableSalespoint
@EnableScheduling
public class InboxChatAppApplication {

	public static void main(String[] args) {
		new File(FileUploadController.uploadDirectory).mkdir();
		SpringApplication.run(InboxChatAppApplication.class, args);
	}

}
