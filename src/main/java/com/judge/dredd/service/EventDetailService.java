package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.EventDetailDTO;

public interface EventDetailService {
	EventDetailDTO getOne(long id);
	EventDetailDTO save(EventDetailDTO eventDetailDTO);
	void delete(long id);
	List<EventDetailDTO> getAll();
}
