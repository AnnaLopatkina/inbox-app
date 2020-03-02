package com.inbox.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.inbox.app.controller.Mail;

@Controller
public class UserController {
	
	private final Mail mail ;
	private final UserManagement userManagement;
	
	@Autowired
    private final ActiveUserStore activeUserStore;
	
	public UserController(UserManagement userManagement , Mail mail , ActiveUserStore activeUserStore) {
		this.userManagement = userManagement ;
		this.mail = mail ;
		this.activeUserStore = activeUserStore ;
	}
	
	@GetMapping("/users")
	public String showUsers(Model model  , Authentication authentication) {
		model.addAttribute("users" , userManagement.findAll());
		model.addAttribute("auth" , (authentication != null) ? authentication : null);
		
		getAllConnectedUsers();
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
			setTimeout(() -> sendConfirmEmail(form.getEmail()), 200);
		}
		return "login";
	}
	
	// Soll verifizieren ob den Formular richtig angelegt ist . 
	private boolean verifyForm (UserForm form) {
		return true ;
	}
	
	private void sendConfirmEmail(String email) {
		try {
			mail.sendMail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setTimeout(Runnable runnable, int delay){
	    new Thread(() -> {
	        try {
	            Thread.sleep(delay);
	            runnable.run();
	        }
	        catch (Exception e){
	            System.err.println(e);
	        }
	    }).start();
	}
	
	private void getAllConnectedUsers() {
		System.err.println(activeUserStore.getUsers().size());
		
	}
	
	
	
}
