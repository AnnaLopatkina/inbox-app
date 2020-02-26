package com.inbox.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String showIndex() {
		return "login";
	}
	
	@GetMapping("/sign-up")
	public String showSignup() {
		return "sign-up";
	}
	
	@GetMapping("/main")
	public String showMainChat() {
		return "main-chat";
	}
}
