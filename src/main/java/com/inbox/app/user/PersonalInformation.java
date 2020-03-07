package com.inbox.app.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PersonalInformation {
	
	@Id
	@GeneratedValue
	private long personalId;
	String city ;
	String job ;
	String phone ;
	String adress ;
	String email ;
	String birthday ;
	Gender gender ;
	String description ; 
	
	@ElementCollection
	Set<Hobby> hobbies ;
	
	PersonalInformation(){
		city = null;
		job = null;
		phone = null;
		adress = null;
		email = null;
		birthday = null;
		gender = null;
		description = null; 
		hobbies = null ;
	}
	
	PersonalInformation(String city , String job , String phone , String adress ,String email ,String birthday ,
						Gender gender ,String description ){
		this.city = city ;
		this.job = job ; 
		this.phone = phone ;
		this.adress = adress ;
		this.email = email ;
		this.birthday = birthday ;
		this.gender = gender ;
		this.description = description ;
		this.hobbies = new HashSet<>() ;
	
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}
}
