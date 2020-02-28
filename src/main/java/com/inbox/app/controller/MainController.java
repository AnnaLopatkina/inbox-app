package com.inbox.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String showIndex() {
		return "redirect:/chat";
	}
	
	@GetMapping("/chat")
	public String showChat() {
		return "chat";
	}
	
}
