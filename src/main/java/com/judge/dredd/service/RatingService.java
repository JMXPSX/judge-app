package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.RateDTO;

public interface RatingService {

	public String update(RateDTO rateDTO);
	
	public RateDTO getRating(long eventId, long entryId, long appuserId);
	
	public List<RateDTO> getRating(long eventId, long appuserId);
}
