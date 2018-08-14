package com.vote.dto;

import com.vote.model.Chain;

public class VoteChainDTO {

	private VoteDTO voteDTO;
	private Chain chain;
	
	public VoteDTO getVoteDTO() {
		return voteDTO;
	}
	public void setVoteDTO(VoteDTO voteDTO) {
		this.voteDTO = voteDTO;
	}
	public Chain getChain() {
		return chain;
	}
	public void setChain(Chain chain) {
		this.chain = chain;
	}
	
	
}
