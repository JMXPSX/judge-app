package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.display.dto.DisplayEventDTO;
import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.dto.EventDetailDTO;
import com.judge.dredd.dto.ScoreDTO;
import com.judge.dredd.service.EntryService;
import com.judge.dredd.service.EventDetailService;
import com.judge.dredd.service.EventService;
import com.judge.dredd.service.ScoreService;

@Service
public class EventServiceImpl implements EventService{

	@Autowired
	private EventDetailService eventDetailService;
	
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private ScoreService scoreService;
	
	@Override
	public DisplayEventDTO getAllForDisplay(long eventId, long judgeId) {
		DisplayEventDTO displayEventDTO = new  DisplayEventDTO();
		
		//1. get the event
		EventDetailDTO event = eventDetailService.getOne(eventId);
		displayEventDTO.setEventName(event.getEventName());
		displayEventDTO.setEventId(event.getId());
		displayEventDTO.setJudgeId(judgeId);
		displayEventDTO.setEntries(new ArrayList<>());
		
		List<EntryDTO> entries = entryService.getAllEntriesByEventId(eventId);
		for(EntryDTO e :entries){
			
		}
		
		List<ScoreDTO> scores = scoreService.getScoresByEventIdAndJudgeId(eventId, judgeId);
		
		return displayEventDTO;
	}
	
	private void populate(DisplayEventDTO display, EventDetailDTO event){
		display.setEventId(event.getId());
		//display.setFinal();
	}

}
