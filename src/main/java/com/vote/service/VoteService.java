package com.vote.service;

import com.vote.dto.VoteDTO;
import com.vote.model.Chain;

public interface VoteService {

	public String vote(long eventId, long participantId, String boothIds);
	
	public VoteDTO getResults(long eventId);
	
	public String callChain(Chain chain);
}
