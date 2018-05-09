package com.judge.dredd.display.dto;

import java.util.List;

public class DisplayEntriesDTO {

	private long entryId;
	private String entryName;
	private String entryDescription;
	private String entryOwner;
	private boolean isDone;
	private List<DisplayCriteriaDTO> criteria;
	private long noteId;
	private String note;
	
	public long getEntryId() {
		return entryId;
	}
	public void setEntryId(long entryId) {
		this.entryId = entryId;
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
	public String getEntryOwner() {
		return entryOwner;
	}
	public void setEntryOwner(String entryOwner) {
		this.entryOwner = entryOwner;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	public List<DisplayCriteriaDTO> getCriteria() {
		return criteria;
	}
	public void setCriteria(List<DisplayCriteriaDTO> criteria) {
		this.criteria = criteria;
	}
	public long getNoteId() {
		return noteId;
	}
	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
