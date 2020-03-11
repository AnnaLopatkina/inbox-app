package com.inbox.app.room;

import java.util.HashSet;
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
	
	
	public void createFriendship(User u1 , User u2 , String description) {
		
		Room room = new Room(u2.getUsername() , RoomType.PRIVATE , description);
				
		userManagement.getUserById(u1.getUserId()).getInformations().getContact()
			.add(userManagement.getUserById(u2.getUserId()));
		
		userManagement.getUserById(u2.getUserId()).getInformations().getContact()
			.add(userManagement.getUserById(u1.getUserId()));
		
		room.getUsersId().add(u1.getUserId());
		room.getUsersId().add(u2.getUserId());
		roomRepository.save(room);
		
		u1.getRoomIds().add(room.getRoomId());
		u2.getRoomIds().add(room.getRoomId());
		
		userManagement.updateUser(u1);
		userManagement.updateUser(u2);
			

	}
	
	public void createGroup(Set<User> users , String roomName , String groupDescription ) {
		Room room = new Room(roomName , RoomType.GROUP , groupDescription);
			for(User u : users) {
				room.getUsersId().add(u.getUserId());
				userManagement.updateUser(u);
			}
			roomRepository.save(room);
			for(User u : users) {
				u.getRoomIds().add(room.getRoomId());
				userManagement.updateUser(u);
			}
	}
	
	public void deleteRoom(Long id){
		for(Room room : roomRepository.findAll()){
			if(room.getRoomId() == id){
				roomRepository.delete(room);
			}
		}
	}
	
	public Set<Room> getUserRooms(Set<Long> roomIds) {
		
		Set<Room> rooms = new HashSet<>();
		for(Long id : roomIds) {
			rooms.add(this.roomRepository.findById(id).get());
		}
		
		return rooms ;
	}
}
