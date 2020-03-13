package com.inbox.app.room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {
	private @Id @GeneratedValue long messageId ;
	private long senderId ;
	private String sms ;
	private Date creationDate = new Date();
	private String imagePath;
	private Long roomId ;
	
	public Message(){};
	
	public Message(long senderId , String sms , String imagePath , Long roomId){
		this.senderId = senderId ;
		this.sms = sms;
		this.imagePath = imagePath;
		this.roomId = roomId;
	}


	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}
	
	public String getCreationDate() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(creationDate);
    }
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public boolean isOwner(Long userId) {
		return userId.equals(senderId);
	}
	
	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

}

