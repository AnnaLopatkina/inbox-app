package com.inbox.app.user;

import java.util.HashSet;

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
		
		userManagement.addUser(new UserForm("boss" , "boss" , "boss", "abc" , "abc" , "boss@abc.com"));
		userManagement.addUser(new UserForm("mboni" , "michael" , "boss" ,"abc" , "abc" , "mbonimichael@yahoo.fr"));
		
		
		userManagement.getUserByEmail("boss@abc.com")
		.updateInfos("Dresden", "Software Entwickler" , "00492254561216" ,
						"St Petersburger Straße 45" , "12.12.1990" , Gender.Male , "I am the Boss" ,  new HashSet<Hobby>(), new HashSet<User>());
		
		userManagement.getUserByEmail("boss@abc.com").getInformations().getContact().add(userManagement.getUserByEmail("mbonimichael@yahoo.fr"));
		userManagement.getUserByEmail("boss@abc.com").getInformations().getHobbies().add(Hobby.IT);
		userManagement.getUserByEmail("boss@abc.com").getInformations().getHobbies().add(Hobby.READING);
		userManagement.getUserByEmail("boss@abc.com").getInformations().getHobbies().add(Hobby.SPORT);
		
		userManagement.getUserByEmail("mbonimichael@yahoo.fr")
		.updateInfos("Berlin", "Web Entwickler" , "00492254561216" ,
						"Berliner Straße 44" , "12.12.1990" , Gender.Male , "I am the Boss Assistant" ,  new HashSet<Hobby>(), new HashSet<User>());
		
		userManagement.getUserByEmail("mbonimichael@yahoo.fr").getInformations().getContact().add(userManagement.getUserByEmail("boss@abc.com"));
		userManagement.getUserByEmail("mbonimichael@yahoo.fr").getInformations().getHobbies().add(Hobby.IT);
		userManagement.getUserByEmail("mbonimichael@yahoo.fr").getInformations().getHobbies().add(Hobby.GAME);
		
	}

}
