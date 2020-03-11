package com.inbox.app.room;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.inbox.app.user.User;
import com.inbox.app.user.UserManagement;

@Service
@Transactional
public class RoomManagement {

	private final RoomRepository roomRepository;
	private final UserManagement userManagement;
	
	public RoomManagement(RoomRepository roomRepository , UserManagement userManagement) {
		this.roomRepository = roomRepository ;
		this.userManagement = userManagement ;
	}
	
	
	public void createFriendship(Set<User> users , String roomName ) {
		Room room ;
		if(users.size() <=2) {
			room = new Room(roomName , RoomType.PRIVATE);
			for(User u : users) {
				room.getUsersId().add(u.getUserId());
				u.getRoomIds().add(room.getRoomId());
				userManagement.updateUser(u);
			}
		}
		else {
			room = new Room(roomName , RoomType.GROUP);
			for(User u : users) {
				room.getUsersId().add(u.getUserId());
				u.getRoomIds().add(room.getRoomId());
				userManagement.updateUser(u);
			}
		}
		
		roomRepository.save(room);
	}
	
	public void deleteRoom(Long id){
		for(Room room : roomRepository.findAll()){
			if(room.getRoomId() == id){
				roomRepository.delete(room);
			}
		}
	}
}
