package com.inbox.app.room;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

interface RoomRepository extends CrudRepository <Room , Long>{
	@Override
	Streamable<Room> findAll();
}
