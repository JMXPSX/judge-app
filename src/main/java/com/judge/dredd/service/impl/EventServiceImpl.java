package com.judge.dredd.service.impl;

import java.util.ArrayList;
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
		List<EventDTO> dtos = new ArrayList();
		List<Event> events = eventRepository.findDistinctByEntries_judges_id(appUserId);
		events.forEach(event -> dtos.add(dtoService.convertToDTO(event)));
		return dtos;
	}


	@Override
	public EventDTO addEvent(EventDTO eventDTO) {
		Event e = dtoService.convertToModel(eventDTO);
		e = eventRepository.save(e);
		return dtoService.convertToDTO(e);
	}
	
	
	
}
