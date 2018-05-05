package com.judge.dredd.dto;

import java.util.List;

public class EventDTO extends TokenDTO{
	
	private int eventId;
	private boolean isEventLive;
	private List<String> judges;
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public boolean isEventLive() {
		return isEventLive;
	}
	public void setEventLive(boolean isEventLive) {
		this.isEventLive = isEventLive;
	}
	public List<String> getJudges() {
		return judges;
	}
	public void setJudges(List<String> judges) {
		this.judges = judges;
	}

	
}
