package com.judge.dredd.dto;

public class EntryDTO extends TokenDTO{

	private Long entryId;
	
	private String entryTitle;
	private String entryDescription;
	private String owner;
	
	private Long eventId;
	
	
	
	public Long getEntryId() {
		return entryId;
	}
	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEntryTitle() {
		return entryTitle;
	}
	public void setEntryTitle(String entryTitle) {
		this.entryTitle = entryTitle;
	}
	public String getEntryDescription() {
		return entryDescription;
	}
	public void setEntryDescription(String entryDescription) {
		this.entryDescription = entryDescription;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

	
}
