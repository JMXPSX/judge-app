package com.judge.dredd.dto;

import java.util.ArrayList;
import java.util.List;

public class EntryDTO {

	private Long entryId;
	private Long eventId;
	private Long categoryId;
	
	private String entryName;
	private String entryDescription;
	
	private boolean isDone;
	
	List<MemberDTO> members;

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public String getEntryDescription() {
		return entryDescription;
	}

	public void setEntryDescription(String entryDescription) {
		this.entryDescription = entryDescription;
	}

	public List<MemberDTO> getMembers() {
		return members;
	}

	public void setMembers(List<MemberDTO> members) {
		this.members = members;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	public void addMember(MemberDTO m){
		if(null == getMembers()){
			setMembers(new ArrayList<>());
		}
		getMembers().add(m);
	}

	@Override
	public String toString() {
		return "EntryDTO [entryId=" + entryId + ", eventId=" + eventId + ", categoryId=" + categoryId + ", entryName="
				+ entryName + ", entryDescription=" + entryDescription + ", isDone=" + isDone + ", members=" + members
				+ "]";
	}	
	
}
