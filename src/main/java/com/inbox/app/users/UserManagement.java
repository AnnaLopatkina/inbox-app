package com.inbox.app.users;

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
		var account = accounts.create(userForm.getEmail(), password, Role.of("USER") );
		
		return userRepository.save(new User(account , userForm.getName() ,userForm.getFirstName() , userForm.getEmail())) ;
	}
	
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User getUserByName(String name) {
		for(User u : userRepository.findAll()) {
			if(u.getName().equals(name)) {
				
				return u ;
			}
		}
		return null ;
	}
	
	public void deleteUser(Long id){
		for(User user : userRepository.findAll()){
			if(user.getUserId() == id){
				userRepository.delete(user);
			}
		}
	}
	
	public Streamable<User> findAll() {
		return userRepository.findAll();
	}
}
