package com.judge.dredd.dto;

public class EntryDTO extends TokenDTO{

	private int entryId;
	private String entryTitle;
	private String entryDescription;
	private String owner;
	
	public int getEntryId() {
		return entryId;
	}
	public void setEntryId(int entryId) {
		this.entryId = entryId;
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
