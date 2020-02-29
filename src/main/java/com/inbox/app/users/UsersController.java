package com.inbox.app.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.inbox.app.controller.Mail;

@Controller
public class UsersController {
	
	private final Mail mail ;
	private final UserManagement userManagement;
	
	public UsersController(UserManagement userManagement , Mail mail ) {
		this.userManagement = userManagement ;
		this.mail = mail ;
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
	
	@PostMapping("/sign-up")
	String getUserForm(UserForm form, Model model){
		
		if(verifyForm(form)) {
			userManagement.addUser(form);	
			try {
				mail.sendMail(form.getEmail());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "login";
	}
	
	// Soll verifizieren ob den Formular richtig angelegt ist . 
	private boolean verifyForm (UserForm form) {
		return true ;
	}
}
