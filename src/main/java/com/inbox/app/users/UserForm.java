package com.inbox.app.users;

public class UserForm {

	private final String name;
	private final String firstName;
	private final String password;
	private final String passwordValid;
	private final String email;
	
	public UserForm(String name , String firstname , String password , String passwordValid , String email){
		this.name = name ;
		this.firstName = firstname ;
		this.email = email ;
		this.password = password ;
		this.passwordValid = passwordValid ;
		
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordValid() {
		return passwordValid;
	}

	public String getEmail() {
		return email;
	}
}
