package com.inbox.app.room;

import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
