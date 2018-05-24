package com.judge.dredd.dto;

import java.util.Date;

public class CommentsDTO {

	private Long commentId;
	private long appUserId;
	private long entryId;
	private String note;
	private Date entryDate;
	
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public long getAppUserId() {
		return appUserId;
	}
	public void setAppUserId(long appUserId) {
		this.appUserId = appUserId;
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
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	
	
}
