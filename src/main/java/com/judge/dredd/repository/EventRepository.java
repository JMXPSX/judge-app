package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
	
	public List<Event> findAllById(long eventId);
	
}
