package com.judge.dredd.service;

import com.judge.dredd.dto.RateDTO;

public interface RatingService {

	public String save(RateDTO rateDTO);
	
	public String update(RateDTO rateDTO);
	
	public RateDTO getRating(long eventId, long categoryId, long entryId);
}
