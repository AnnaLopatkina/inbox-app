package com.inbox.app.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.inbox.app.controller.Mail;

@Controller
public class UserController {
	
	@Autowired
	private SessionRegistry sessionRegistry;
	private List<String> usersname ; 
	private final Mail mail ;
	private final UserManagement userManagement;
	
	public UserController(UserManagement userManagement , Mail mail ) {
		this.userManagement = userManagement ;
		this.mail = mail ;
		this.usersname = null ;
	}
	
	@GetMapping("/users")
	public String showUsers(Model model) {
		model.addAttribute("users" , userManagement.findAll());	
		usersname = getUsersFromSessionRegistry() ;
		model.addAttribute("auth" , (usersname != null) ? usersname : null);
		
		System.err.println(userManagement.getUserByEmail("mbonimichael@yahoo.fr").getInformations().getProfileImagePath());
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
			return "redirect:/login";
		}
		else return "redirect:/sign-up";

	}
	
	@GetMapping("/profile/{id}")
	public String showProfile(@PathVariable Long id , Model model , Authentication authentication) {
		model.addAttribute("user" , userManagement.getUserById(id));
		model.addAttribute("authEmail" , authentication.getName());
		return "profile";
	}
	
	@GetMapping("edit/profile/{id}")
	public String showEditProfile(@PathVariable Long id , Model model , Authentication authentication) {
		
		if(!userManagement.getUserById(id).getEmail().equals(authentication.getName())) {
			return "redirect:/users";
		}
		model.addAttribute("user" , userManagement.getUserById(id));
		model.addAttribute("authEmail" , authentication.getName());
		return "edit-profile";
	}

	// Soll verifizieren ob den Formular richtig angelegt ist
	private boolean verifyForm (UserForm form) {
		// Anja
		return form.getPassword() == form.getPasswordValid();
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

	public List<String> getUsersFromSessionRegistry() {
		 final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
		 List<String> usersname = new ArrayList<>();
		 
	        for(final Object principal : allPrincipals) {
	            if(principal instanceof UserDetails) {
	                final UserDetails user = (UserDetails) principal;
	                usersname.add(user.getUsername());
	            }
	        }
	        return usersname ;
	}
	
	
	
}
