package com.inbox.app.room;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room {
	
	private @Id @GeneratedValue long roomId ;
	private @ElementCollection Set<Long> usersId ;
	private @ElementCollection Set<Message> messages ;
	
	private String roomName ;
	private RoomType roomtype ;
	
	Room(){
		this.messages = new HashSet<>();
		this.usersId = new HashSet<>();
	}
	
	Room(String roomName , RoomType roomtype){
		this.roomName = roomName ;
		this.roomtype = roomtype ;
		this.messages = new HashSet<>();
		this.usersId = new HashSet<>();
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public Set<Long> getUsersId() {
		return usersId;
	}

	public void setUsersId(Set<Long> usersId) {
		this.usersId = usersId;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public RoomType getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(RoomType roomtype) {
		this.roomtype = roomtype;
	};
	
}
