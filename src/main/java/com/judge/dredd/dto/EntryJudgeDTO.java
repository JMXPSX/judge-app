package com.judge.dredd.dto;

import java.util.List;

public class EntryJudgeDTO {

	private Long entryId;
	
	private List<Long> judges;

	public Long getEntryId() {
		return entryId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

	public List<Long> getJudges() {
		return judges;
	}

	public void setJudges(List<Long> judges) {
		this.judges = judges;
	}	
	
}
