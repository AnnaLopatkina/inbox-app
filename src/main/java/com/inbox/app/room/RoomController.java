package com.inbox.app.room;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inbox.app.user.User;
import com.inbox.app.user.UserManagement;

@Controller
public class RoomController {

	private final UserManagement userManagement;
	private final RoomManagement roomManagement;
	private User authUser ;
	public RoomController(UserManagement userManagement , RoomManagement roomManagement) {
		this.roomManagement = roomManagement;
		this.userManagement = userManagement;
	}
	
	@GetMapping("/chat")
	public String showChat(Authentication authentication , Model model) {
		authUser = userManagement.getUserByEmail(authentication.getName());	
		model.addAttribute("rooms", roomManagement.getUserRooms(authUser.getRoomIds()));
		return "chat";
	}
	
}
