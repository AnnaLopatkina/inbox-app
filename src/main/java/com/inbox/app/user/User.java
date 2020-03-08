package com.inbox.app.user;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
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
	}
	public PersonalInformation getInformations() {
		return informations;
	}

	public void setInformations(PersonalInformation informations) {
		this.informations = informations;
	}

	public void updateInfos(String city , String job , String phone , String address ,String birthday ,
			Gender gender ,String description  , Set<Hobby> hobbies , Set<User> contact ) {
		
		this.informations.setCity(city);
		this.informations.setJob(job); 
		this.informations.setPhone(phone);
		this.informations.setaddress(address);
		this.informations.setBirthday(birthday);
		this.informations.setGender(gender);
		this.informations.setDescription(description);
		this.informations.setHobbies(hobbies);
		this.informations.setContact(contact);
		
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
	
	public String toString() {
		String str ="";
		str = "Name: "+this.getName() + "--  Firstname: "+this.getFirstname()+ "-- Username: "+this.getUsername() 
			+"-- Email: "+this.getEmail() ;
		return str ;
	}
}
