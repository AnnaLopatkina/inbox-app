package com.inbox.app.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
	
	private final UserManagement userManagement;
	
	public UsersController(UserManagement userManagement) {
		this.userManagement = userManagement ;
	}
	
	@GetMapping("/users")
	public String showUsers(Model model) {
		model.addAttribute("users" , userManagement.findAll());
		return "users";
	}
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@GetMapping("/sign-up")
	public String showSignup() {
		return "sign-up";
	}
	
	
}
