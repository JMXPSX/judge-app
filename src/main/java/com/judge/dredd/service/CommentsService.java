package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.CommentsDTO;
import com.judge.dredd.model.Comments;

public interface CommentsService {

	public Comments findByEntryIdAndJudgeId(long entryId, long judgeId);
	
	public String updateComments(CommentsDTO commentsDTO);
	
	public List<Comments> findCommentsByEntryId(long entryId);
	
	public void addComment(long entryId, long appUserId);
	
}
