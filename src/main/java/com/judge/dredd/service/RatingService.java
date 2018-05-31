package com.judge.dredd.service;

import com.judge.dredd.dto.RateDTO;

public interface RatingService {

	public String update(RateDTO rateDTO);
	
	public RateDTO getRating(long eventId, long entryId, long appuserId);
}
