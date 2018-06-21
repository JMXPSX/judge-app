package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.EventDTO;

public interface EventService {
	
	public List<EventDTO> getEventsByUser(long appUserId);
	
	public EventDTO addEvent(EventDTO eventDTO);
}
