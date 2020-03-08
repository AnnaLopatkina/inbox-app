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
	private String city ;
	private String job ;
	private String  phone ;
	private String adress ;
	private String birthday ;
	private Gender gender ;
	private String description ; 
	 
	@ElementCollection
	private Set<Hobby> hobbies ;
	
	@ElementCollection
	private Set<User> contact ;

	private String profileImagePath;


	PersonalInformation(){
		this.city = "";
		this.job = "";
		this.phone = "";
		this.adress = "";
		this.birthday = "";
		this.gender = null;
		this.description = ""; 
		this.hobbies = new HashSet<>();
		this.contact = new HashSet<>();
		this.profileImagePath = "profile-1.png" ;
	}
	
	PersonalInformation(String city , String job , String phone , String adress ,String birthday ,
						Gender gender ,String description ){
		this.city = city ;
		this.job = job ; 
		this.phone = phone ;
		this.adress = adress ;
		this.birthday = birthday ;
		this.gender = gender ;
		this.description = description ;
		this.hobbies = new HashSet<>();
		this.contact = new HashSet<>();
		this.profileImagePath = "profile-1.png";
	}

	
	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
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
	
	public Set<User> getContact() {
		return contact;
	}

	public void setContact(Set<User> contact) {
		this.contact = contact;
	}
	
	public String toString() {
		String str = "" ; 
		
		str = "city: " + this.city + ", job: " + this.job + ", phone: "+this.phone+ ", adress: "+this.adress+", birthday: "+this.birthday +
		", gender: "+this.gender+", description: "+this.description;
		return str ;
	}
}
