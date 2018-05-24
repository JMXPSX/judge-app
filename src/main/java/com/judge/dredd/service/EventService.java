package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.display.dto.DisplayScoreSummaryDTO;
import com.judge.dredd.dto.EventDTO;

public interface EventService {
	
	
	public DisplayScoreSummaryDTO getFinalizedScoreSummary(long eventId);
	
	public List<EventDTO> getEventsByUser(long appUserId);
}
