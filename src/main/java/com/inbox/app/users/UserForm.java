package com.inbox.app.users;

public class UserForm {

	private final String name;
	private final String firstname;
	private final String username;
	private final String password;
	private final String passwordValid;
	private final String email;
	
	public UserForm(String name , String firstname , String username , String password , String passwordValid , String email){
		this.name = name ;
		this.firstname = firstname ;
		this.email = email ;
		this.password = password ;
		this.passwordValid = passwordValid ;
		this.username = username;
		
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public String getFirstname() {
		return firstname;
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
