package com.inbox.app.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.inbox.app.controller.Mail;

@Controller
public class UserController {
	
	@Autowired
	//private final SearchUser users;
	private SessionRegistry sessionRegistry;
	private List<String> usersname ; 
	private final Mail mail ;
	private final UserManagement userManagement;
	private boolean personWithSuchNameNotFound;
	private Set <User> peopleFound = new HashSet<>();;
	//private Stream<User> peopleFound;
	//private Streamable<User> peopleFound;
	//private Set <User> found;
	private UserRepository users;
	private long count;
	
	public UserController(UserManagement userManagement , Mail mail, UserRepository searchUser ) {
		this.users= searchUser;
		this.userManagement = userManagement ;
		this.mail = mail ;
		this.usersname = null ;
		this.personWithSuchNameNotFound = true;
	}
	
	@GetMapping("/users")
	public String showUsers(Model model) {
		model.addAttribute("users" , userManagement.findAll());
		usersname = getUsersFromSessionRegistry() ;
		model.addAttribute("auth" , usersname);
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

	@GetMapping("/search")
	public String search(Model model){
		model.addAttribute("personNotFound", personWithSuchNameNotFound);
		model.addAttribute("peopleFound" , peopleFound);
		System.err.println(peopleFound + "who is found");
		return "search";
	}

	@GetMapping("/search/name")
	public String searchName(Model model){
		model.addAttribute("personNotFound", personWithSuchNameNotFound);
		model.addAttribute("peopleFound" , peopleFound);
		//System.out.println(peopleFound + "gggggggg");

		return "search";
	}

	@RequestMapping("/search/name")
	String search(@RequestParam("name") String name , Model model) {
		peopleFound.clear();
		if(!(name == null) && !name.isEmpty()) {
			//for(String str : name.split(" ") ) {
				//peopleFound.addAll(users.findBySuffix(searchTraitement(str)).toList());
				//peopleFound.addAll(users.findBySuffix(str).toList());

				//peopleFound.addAll(users.findByInfix(searchTraitement(str)).toList());
				//peopleFound.addAll(users.findByInfix(str).toList());

				//peopleFound.addAll(users.findByPrefix(searchTraitement(str)).toList());
				//peopleFound.addAll(users.findByPrefix(str).toList());

			   peopleFound = userManagement.findAll().stream().filter(n -> n.getName().startsWith(name)).collect(Collectors.toSet());
			   //System.err.println(peopleFound);
			   //System.err.println(peopleFound.size());
		}
		if(peopleFound.size() > 0){
			personWithSuchNameNotFound = false;
		}
		else personWithSuchNameNotFound = true;
		//System.out.println(personWithSuchNameNotFound);
		return "redirect:/search";
	}

	/*private Set <User>  findNameByPrefix(String str){
		found = new HashSet<>();
		for(User u: users.findAll()){
			if(u.getName().startsWith(str))
			{
				found.add(u);
				//System.err.println(found);
			}
		}
		return found;
	}*/

	private String searchTraitement(String str) {

		str = str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
		return str ;
	}

	private boolean verifyForm (UserForm form) {
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
	
		Set<Hobby> hobbySet = new HashSet<>();
		
		if(!(hobbies == null)) {
			
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
		}
		return hobbySet;
	}
}
