package com.judge.dredd.service;

import com.judge.dredd.dto.CommentsDTO;
import com.judge.dredd.model.Comments;

public interface CommentsService {

	public Comments findByEntryIdAndJudgeId(long entryId, long judgeId);
	
	public String updateComments(CommentsDTO commentsDTO);
}
