package com.vote.service;

import com.vote.dto.VoteDTO;

public interface VoteService {

	public String vote(long eventId, long participantId, String boothIds);
	
	public VoteDTO getResults(long eventId);
}
