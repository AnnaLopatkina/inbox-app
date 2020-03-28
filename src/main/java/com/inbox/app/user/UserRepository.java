package com.inbox.app.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.Collection;

interface  UserRepository extends CrudRepository <User , Long>{
	@Override
	Streamable<User> findAll();

}
