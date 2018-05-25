package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.CommentsDTO;

public interface CommentsService {

	public CommentsDTO updateComments(CommentsDTO commentsDTO);
	
	public List<CommentsDTO> findCommentsByEntryId(long entryId);
	
	public CommentsDTO addComment(CommentsDTO commentsDTO);
	
}
