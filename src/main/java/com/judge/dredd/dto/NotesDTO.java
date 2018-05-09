package com.judge.dredd.dto;

public class NotesDTO {

	private Long noteId;
	private long judgeId;
	private long entryId;
	private String note;
	public Long getNoteId() {
		return noteId;
	}
	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}
	public long getJudgeId() {
		return judgeId;
	}
	public void setJudgeId(long judgeId) {
		this.judgeId = judgeId;
	}
	public long getEntryId() {
		return entryId;
	}
	public void setEntryId(long entryId) {
		this.entryId = entryId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
