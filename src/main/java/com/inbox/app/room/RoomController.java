package com.inbox.app.room;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inbox.app.user.User;
import com.inbox.app.user.UserManagement;

@Controller
public class RoomController {

	private final UserManagement userManagement;
	private final RoomManagement roomManagement;
	private User authUser ;
	private Long activeRoomId ;
	
	public RoomController(UserManagement userManagement , RoomManagement roomManagement) {
		this.roomManagement = roomManagement;
		this.userManagement = userManagement;
	}
	
	@GetMapping("/chat")
	public String showChat(Authentication authentication , Model model) {
		authUser = userManagement.getUserByEmail(authentication.getName());
		model.addAttribute("authId", authUser.getUserId());
		model.addAttribute("rooms", roomManagement.getUserRooms(authUser.getRoomIds()));
		model.addAttribute("userManagement", userManagement);
		model.addAttribute("private" , RoomType.PRIVATE);
		model.addAttribute("activeRoomId" , activeRoomId);
		if(activeRoomId != null) {
			model.addAttribute("activeRoom" , roomManagement.getRoomById(activeRoomId));
		}else {
			model.addAttribute("activeRoom" , null);
		}
		
		return "chat";
	}
	
	@GetMapping("/roomId/{id}")
	public String getRoomID (@PathVariable Long id) {
		activeRoomId = id ;
		return "redirect:/chat";
	}
	@GetMapping("/openDiscution/{id}")
	public String openDiscution(Authentication authentication , @PathVariable Long id) {
		authUser = userManagement.getUserByEmail(authentication.getName());
		roomManagement.createFriendship(authUser, userManagement.getUserById(id), "Arrive");
		return "redirect:/chat";
	}
	
	@GetMapping("/create-group")
	public String showGroupCreation(Authentication authentication  , Model model) {
		authUser = userManagement.getUserByEmail(authentication.getName());
		model.addAttribute("users" , userManagement.findAll());	
		model.addAttribute("auth",authUser);
		return "group-creation";
	}

	@PostMapping("/create-group")
	public String createGroup(GroupForm form , Authentication authentication ,
			@RequestParam(value="idChecked" , required = false) List<String> usersId){
		
		authUser = userManagement.getUserByEmail(authentication.getName());
		Set<User> users= new HashSet<>();
		if(usersId != null){
	        for(String ui : usersId){
	            users.add(userManagement.getUserById(Long.parseLong(ui)));
	        }
	        users.add(authUser);
	        roomManagement.createGroup(users, form.getGroupName(), form.getGroupDescription());
	    }
	    return "redirect:/chat";
	}
	
	@GetMapping("quit-group/{id}")
	public String quitGroup(Authentication authentication , @PathVariable Long id ) {
		roomManagement.quitGroup(userManagement.getUserByEmail(authentication.getName()) , id);
		activeRoomId = null ;
		return "redirect:/chat";
	}
	
	
	@PostMapping("/send-message")
	public String receiveMessage(Authentication authentication , Model model ,
			@RequestParam(value="message" , required = true) String sms){
		
		model.addAttribute("message",sms);
		if(activeRoomId != null) {
			if(!sms.isBlank() || !sms.isEmpty()) {
				authUser = userManagement.getUserByEmail(authentication.getName());
				roomManagement.sendMessage(activeRoomId, new Message(authUser.getUserId(), sms ,
						authUser.getInformations().getProfileImagePath()));
				
			}			
		}
		return "redirect:/chat";
	}	
}
