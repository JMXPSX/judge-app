package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.EventDetailDTO;
import com.judge.dredd.model.EventDetail;
import com.judge.dredd.repository.EventDetailRepository;
import com.judge.dredd.service.DtoService;
import com.judge.dredd.service.EventDetailService;

@Service
public class EventDetailServiceImpl implements EventDetailService{
	
	@Autowired
	private EventDetailRepository eventDetailRepository;
	
	@Autowired
	private DtoService dtoService;

	@Override
	public EventDetailDTO getOne(long id) {
		return dtoService.convertToDTO(eventDetailRepository.findById(id).get());
	}

	@Override
	public EventDetailDTO save(EventDetailDTO eventDetailDTO) {
		
		EventDetail eventDetail = dtoService.convertToModel(eventDetailDTO);
		
		eventDetail = eventDetailRepository.save(eventDetail);
		return dtoService.convertToDTO(eventDetail);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EventDetailDTO> getAll() {
		List<EventDetailDTO> eventDetailsDTOs = new ArrayList<>();
		
		List<EventDetail> eventDetails = (List<EventDetail>) eventDetailRepository.findAll();
		eventDetails.forEach(event -> eventDetailsDTOs.add(dtoService.convertToDTO(event)));
		
		return eventDetailsDTOs;
	}

}
