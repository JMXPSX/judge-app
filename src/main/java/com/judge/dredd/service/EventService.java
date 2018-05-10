package com.judge.dredd.service;

import com.judge.dredd.display.dto.DisplayEventDTO;
import com.judge.dredd.display.dto.DisplayScoreSummaryDTO;

public interface EventService {

	public DisplayEventDTO getAllForDisplay(long eventId, long judgeId);
	
	public DisplayScoreSummaryDTO getFinalizedScoreSummary(long eventId);
}
