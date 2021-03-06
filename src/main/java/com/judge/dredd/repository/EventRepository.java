package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
	
	@Query(value = "select e.* from event e where e.event_id = ?1", nativeQuery = true)
	public List<Event> findAllById(long eventId);
	
	public List<Event> findDistinctByEntries_judges_userId(long userId);
	
}
