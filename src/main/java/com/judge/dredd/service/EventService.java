package com.judge.dredd.service;

import com.judge.dredd.display.dto.DisplayEventDTO;

public interface EventService {

	public DisplayEventDTO getAllForDisplay(long eventId, long judgeId);
}
