package com.inbox.app.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountIdentifier;

@Entity
public class User {
	
	private @Id @GeneratedValue long userId ;
	private String name;
	private String firstname;
	private String username ;
	private String email;
	private String account;
	private ArrayList<Role> roles;
	private UserAccountIdentifier accountId;
	private Long activeRoomId ;
	private @ElementCollection Set<Long> roomIds ;
	


	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "personalId")
	private PersonalInformation informations;

	@SuppressWarnings("unused")
	private User() {} 
	
	public User(UserAccount account , String name , String firstname , String username , String email ) {
		this.account = account.getUsername();
		this.accountId = account.getId();
		this.roles = new ArrayList<>(account.getRoles().toList());
		this.firstname = firstname ;
		this.name = name ;
		this.email = email ;
		this.username = username;
		this.informations = new PersonalInformation();
		this.roomIds = new HashSet<>();
		this.activeRoomId = null;
	}

	public PersonalInformation getInformations() {
		return informations;
	}

	public void setInformations(PersonalInformation informations) {
		this.informations = informations;
	}

	public void updateInfos(String city , String job , String phone , String address ,String birthday ,
			Gender gender ,String description  , Set<Hobby> hobbies  ) {
		
		if(city != null)
			this.informations.setCity(city);
		if(job != null)
			this.informations.setJob(job); 
		if(phone != null)
			this.informations.setPhone(phone);
		if(address != null)
			this.informations.setAddress(address);
		if(birthday != null)
			this.informations.setBirthday(birthday);
		if(gender != null)
			this.informations.setGender(gender);
		if(description != null)
			this.informations.setDescription(description);
		
		if(hobbies != null)
			this.informations.setHobbies(hobbies);
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public ArrayList<Role> getRoles() {
		return roles;
	}

	public void setRoles(ArrayList<Role> roles) {
		this.roles = roles;
	}

	public UserAccountIdentifier getAccountId() {
		return accountId;
	}

	public void setAccountId(UserAccountIdentifier accountId) {
		this.accountId = accountId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<Long> getRoomIds() {
		return roomIds;
	}

	public void setRoomIds(Set<Long> roomIds) {
		this.roomIds = roomIds;
	}
	
	public Long getActiveRoomId() {
		return activeRoomId;
	}

	public void setActiveRoomId(Long activeRoomId) {
		this.activeRoomId = activeRoomId;
	}

	
	public boolean hasHobby(String hobby) {
		for(Hobby h : this.informations.getHobbies()) {
			if (h.toString().equals(hobby))
				return true;
		}
		return false;
	}
	public String toString() {
		String str ="";
		str = "Name: "+this.getName() + "--  Firstname: "+this.getFirstname()+ "-- Username: "+this.getUsername() 
			+"-- Email: "+this.getEmail() ;
		return str ;
	}
}
