package com.inbox.app.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("userManagement" , userManagement);
		return "profile";
	}
	
	@GetMapping("profile-reload/{id}")
	public String showProfileReload(@PathVariable Long id){
		
		try{
		    Thread.sleep(2000);
		}
		catch(InterruptedException ex){
		    Thread.currentThread().interrupt();
		}
		
		return "redirect:/profile/" + id;
	}
	
	@GetMapping("/user_profile")
	public String redirectToProfile(Authentication authentication) {
		Long id = userManagement.getUserByEmail(authentication.getName()).getUserId();
		return "redirect:/profile/" + id;
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

	@PostMapping("/edit-user/{id}")
	public String addEditForm(@RequestParam(value="idChecked" , required = false) List<String> hobbies ,
							  @PathVariable Long id, EditForm form, Model model, Authentication authentication){
		User user = userManagement.getUserById(id);
		model.addAttribute("user" , userManagement.getUserById(id));
		model.addAttribute("authEmail" , authentication.getName());
		userManagement.editUserInfo(user, form , convertToHobby(hobbies));
		return "redirect:/profile/" + id;
	}

	// Soll verifizieren ob den Formular richtig angelegt ist
	private boolean verifyForm (UserForm form) {
		// Anja
		return form.getPassword().equals(form.getPasswordValid());
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
	
	private Set<Hobby> convertToHobby(List<String> hobbies){
		System.err.println("hobbies>>> " + hobbies);

		Set<Hobby> hobbySet = new HashSet<>();
		for(String str: hobbies){
			if(str.equals(Hobby.IT.toString())){
				hobbySet.add(Hobby.IT);
			}
			if(str.equals(Hobby.SPORT.toString())){
				hobbySet.add(Hobby.SPORT);
			}
			if(str.equals(Hobby.DANCING.toString())){
				hobbySet.add(Hobby.DANCING);
			}
			if(str.equals(Hobby.GAME.toString())){
				hobbySet.add(Hobby.GAME);
			}
			if(str.equals(Hobby.READING.toString())){
				hobbySet.add(Hobby.READING);
			}
			if(str.equals(Hobby.TRAVELLING.toString())){
				hobbySet.add(Hobby.TRAVELLING);
			}
			if(str.equals(Hobby.MUSIC.toString())){
				hobbySet.add(Hobby.MUSIC);
			}
		}
		return hobbySet;
	}
}
