package com.inbox.app.room;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

interface MessageRepository extends CrudRepository<Message, Long>{
	@Override
	Streamable<Message> findAll();
}
