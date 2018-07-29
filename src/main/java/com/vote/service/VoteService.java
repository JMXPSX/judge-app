package com.vote.service;

import com.vote.dto.VoteDTO;

public interface VoteService {

	public String vote(long eventId, long participantId, long boothId);
	
	public VoteDTO getResults(long eventId);
}
