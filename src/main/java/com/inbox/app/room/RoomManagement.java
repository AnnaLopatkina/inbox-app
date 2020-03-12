package com.inbox.app.room;

import java.util.LinkedHashSet;
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
		
		if(testFriendShip(u1 , u2)){
			Room room = new Room(u2.getUsername() , RoomType.PRIVATE , description);
			
			u1.getInformations().getContact().add(u2.getUserId());
			u2.getInformations().getContact().add(u1.getUserId());
			room.getUsersId().add(u1.getUserId());
			room.getUsersId().add(u2.getUserId());
			roomRepository.save(room);
			u1.getRoomIds().add(room.getRoomId());
			u2.getRoomIds().add(room.getRoomId());
			
			userManagement.updateUser(u1);
			userManagement.updateUser(u2);
		}
		
	}
	
	public void createGroup(Set<User> users , String roomName , String groupDescription ) {
		Room room = new Room(roomName , RoomType.GROUP , groupDescription);
			for(User u : users) {
				room.getUsersId().add(u.getUserId());
			}
			roomRepository.save(room);
			for(User u : users) {
				u.getRoomIds().add(room.getRoomId());
				userManagement.updateUser(u);
			}
			
			System.err.println("Groupe erzeugt");
	}
	
	public void deleteRoom(Long id){
		for(Room room : roomRepository.findAll()){
			if(room.getRoomId() == id){
				roomRepository.delete(room);
			}
		}
	}
	
	public Room getRoomById(Long id) {
		return roomRepository.findById(id).get();
	}
	public Set<Room> getUserRooms(Set<Long> roomIds) {
		
		Set<Room> rooms = new LinkedHashSet<>();
		for(Long id : roomIds) {
			rooms.add(this.roomRepository.findById(id).get());
		}
		
		return rooms ;
	}
	
	private boolean testFriendShip(User u1 , User u2) {
		for(Long id : u1.getRoomIds()) {
			if(getRoomById(id).getRoomtype().equals(RoomType.PRIVATE)) {
				if(getRoomById(id).getFriendId(u1.getUserId()).equals(u2.getUserId()))
					return false;
			}
		}
		return true ;
	}
}
