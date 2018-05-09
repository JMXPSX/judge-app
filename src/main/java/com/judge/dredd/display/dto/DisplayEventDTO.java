package com.judge.dredd.display.dto;

import java.util.List;

public class DisplayEventDTO {

	private long eventId;
	private long judgeId;
	private String eventName;
	private List<DisplayEntriesDTO> entries;
	private boolean isFinal;
	
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public List<DisplayEntriesDTO> getEntries() {
		return entries;
	}
	public void setEntries(List<DisplayEntriesDTO> entries) {
		this.entries = entries;
	}
	public boolean isFinal() {
		return isFinal;
	}
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public long getJudgeId() {
		return judgeId;
	}
	public void setJudgeId(long judgeId) {
		this.judgeId = judgeId;
	}
	
	
}
