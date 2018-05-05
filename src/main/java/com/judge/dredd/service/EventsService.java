package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.EventDetailDTO;

public interface EventsService {
	EventDetailDTO getOne(long id);
	EventDetailDTO save(EventDetailDTO eventDetailDTO);
	void delete(long id);
	List<EventDetailDTO> getAll();
}
