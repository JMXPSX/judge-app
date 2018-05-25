package com.judge.dredd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.EventDTO;
import com.judge.dredd.model.Event;
import com.judge.dredd.repository.EventRepository;
import com.judge.dredd.service.DtoService;
import com.judge.dredd.service.EventService;


@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private DtoService dtoService;
		

	@Override
	public List<EventDTO> getEventsByUser(long appUserId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public EventDTO addEvent(EventDTO eventDTO) {
		Event e = dtoService.convertToModel(eventDTO);
		e = eventRepository.save(e);
		return dtoService.convertToDTO(e);
	}
	
	
	
}
