package com.inbox.app.socket;

public class SocketMessage {
	private String sender ;
	private String message ;
	private String roomId ;
	private String profile ;
	private String username;
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRoomId() {
		return roomId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	public String toString() {
		return "sender: "+sender+", message: "+message+", roomId: "+roomId+", profile: "+profile+", username: "+username;
	}
	
}
