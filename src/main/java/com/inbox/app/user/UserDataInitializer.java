package com.inbox.app.user;

import java.util.HashSet;

import org.salespointframework.core.DataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.inbox.app.room.RoomManagement;

@Component
@Order(10)
public class UserDataInitializer implements DataInitializer{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserDataInitializer.class);
	private final UserManagement userManagement ;
	private final RoomManagement roomManagement ;
	UserDataInitializer(UserManagement userManagement , RoomManagement roomManagement){
		this.userManagement = userManagement ;
		this.roomManagement = roomManagement ;
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		LOG.info("Creating default Users.");

		/* INIT BOSS */
		userManagement.addUser(new UserForm("boss" , "boss" , "boss", "abc" , "abc" , "boss@abc.com"));
		userManagement.getUserByEmail("boss@abc.com")
		.updateInfos("Dresden", "Software Entwickler" , "00492254561216" ,
						"St Petersburger Straße 45" , "12.12.1990" , Gender.Male , "I am the Boss" ,  new HashSet<Hobby>(), new HashSet<Long>());
		
		userManagement.getUserByEmail("boss@abc.com").getInformations().getHobbies().add(Hobby.IT);
		userManagement.getUserByEmail("boss@abc.com").getInformations().getHobbies().add(Hobby.READING);
		userManagement.getUserByEmail("boss@abc.com").getInformations().getHobbies().add(Hobby.SPORT);
		
		/* INIT MICHAEL */
		userManagement.addUser(new UserForm("mboni" , "michael" , "micky" ,"abc" , "abc" , "mbonimichael@yahoo.fr"));
		userManagement.getUserByEmail("mbonimichael@yahoo.fr")
		.updateInfos("Berlin", "Web Entwickler" , "00492254561216" ,
						"Berliner Straße 44" , "12.12.1990" , Gender.Male , "I am the Boss Assistant" ,  new HashSet<Hobby>(), new HashSet<Long>());
		userManagement.getUserByEmail("mbonimichael@yahoo.fr").getInformations().getHobbies().add(Hobby.IT);
		userManagement.getUserByEmail("mbonimichael@yahoo.fr").getInformations().getHobbies().add(Hobby.GAME);
		
		/* INIT VASKO */
		userManagement.addUser(new UserForm("vasil" , "petrov" , "vasko" ,"abc" , "abc" , "vasilpetrov@abc.com"));
		userManagement.getUserByEmail("vasilpetrov@abc.com")
		.updateInfos("Paris", "Frontend Entwickler" , "00492254561244" ,
						"Route Budapest 12" , "01.01.1970" , Gender.Male , "I am the future Boss" ,  new HashSet<Hobby>(), new HashSet<Long>());
		userManagement.getUserByEmail("vasilpetrov@abc.com").getInformations().getHobbies().add(Hobby.IT);
		
		/* INIT AMAR */
		userManagement.addUser(new UserForm("amar" , "boris" , "amBo" ,"abc" , "abc" , "amarboris@abc.com"));
		userManagement.getUserByEmail("amarboris@abc.com")
		.updateInfos("Sophia", "Mobile App Entwickler" , "00492254561244" ,
						"sophia Straße 40" , "01.01.1970" , Gender.Male , "I am the future Boss assistant" ,  new HashSet<Hobby>(), new HashSet<Long>());
		userManagement.getUserByEmail("amarboris@abc.com").getInformations().getHobbies().add(Hobby.IT);
		
		
		
		/* CREATE FREINDSHIP */
		roomManagement.createFriendship(userManagement.getUserByEmail("boss@abc.com"),
				userManagement.getUserByEmail("mbonimichael@yahoo.fr"), "Business");
		
	}

}
