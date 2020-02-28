package com.inbox.app.users;

import java.util.List;

import org.salespointframework.core.DataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
public class UserDataInitializer implements DataInitializer{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserDataInitializer.class);
	private final UserManagement userManagement ;
	
	UserDataInitializer(UserManagement userManagement){
		this.userManagement = userManagement ;
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		LOG.info("Creating default Users.");
		
		List.of(
				new UserForm("boss" , "boss" , "abc" , "abc" , "boss@abc.com"),
				new UserForm("mboni" , "michael" , "abc" , "abc" , "mbonimichael@yahoo.fr")
		).forEach(userManagement::addUser);
	}

}
