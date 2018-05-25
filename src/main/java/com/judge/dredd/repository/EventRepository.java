package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
	
	public List<Event> findAllById(long eventId);
	public List<Event> findDistinctByEntries_judges_username(String username);


	//public List<Event> findByByAppUserId(long appUserId);
	
}
