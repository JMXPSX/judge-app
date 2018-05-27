package com.judge.dredd.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommentsDTO {

	private Long commentId;
	private long appUserId;
	private long entryId;
	private String comment;
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date commentDate;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
		
}
