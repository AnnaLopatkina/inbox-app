package com.inbox.app.user;

import javax.transaction.Transactional;

import org.salespointframework.useraccount.Password.UnencryptedPassword;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserManagement {
	private final UserRepository userRepository;
	private final UserAccountManager accounts;
	
	public UserManagement(UserRepository userRepository , UserAccountManager accounts) {
		this.userRepository = userRepository ;
		this.accounts = accounts ;
	}
	
	public User addUser(UserForm userForm) {
		var password = UnencryptedPassword.of(userForm.getPassword());
		var  account = accounts.create(userForm.getEmail(), password, Role.of("USER") );
		
		User toSave = new User(account , userForm.getName() ,userForm.getFirstname() , userForm.getUsername() , userForm.getEmail());
		return userRepository.save(toSave) ;
	}
	
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	/* ACCOUNT USERNAME = EMAIL */
	public User getUserByEmail(String email) {
		for(User u : userRepository.findAll()) {
			if(u.getEmail().equals(email)) {
				return u ;
			}
		}
		return null ;
	}
	
	/* ACCOUNT USERNAME = EMAIL */
	public void deleteUser(Long id){
		for(User user : userRepository.findAll()){
			if(user.getUserId() == id){
				userRepository.delete(user);
				accounts.delete(accounts.findByUsername(user.getAccount()).get());
			}
		}
	}
	
	public Streamable<User> findAll() {
		return userRepository.findAll();
	}
}
