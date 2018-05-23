package com.judge.dredd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.CommentsDTO;
import com.judge.dredd.model.Comments;
import com.judge.dredd.repository.CommentsRepository;
import com.judge.dredd.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {
	
	@Autowired
	CommentsRepository commentsRepository;

	@Override
	public Comments findByEntryIdAndJudgeId(long entryId, long judgeId) {
		Comments n = commentsRepository.findByEntryIdAndJudgeId(entryId, judgeId);
		return n;
	}

	@Override
	public String updateComments(CommentsDTO notesDTO) {
		Comments n = commentsRepository.findById(notesDTO.getNoteId()).get();
		
		if(n != null){
			n.setNote(notesDTO.getNote());
			commentsRepository.save(n);
		}
		
		return "message: Done";
	}

}
