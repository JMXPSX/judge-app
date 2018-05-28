package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.CommentsDTO;
import com.judge.dredd.model.Comments;
import com.judge.dredd.repository.CommentsRepository;
import com.judge.dredd.service.CommentsService;
import com.judge.dredd.service.DtoService;

@Service
public class CommentsServiceImpl implements CommentsService {
	
	@Autowired
	private CommentsRepository commentsRepository;
	
	@Autowired
	private DtoService dtoService;
	
	@Override
	public CommentsDTO updateComments(CommentsDTO notesDTO) {
		Comments n = commentsRepository.findById(notesDTO.getCommentId()).get();
		
		if(n != null){
			n.setComment(notesDTO.getComment());
			n = commentsRepository.save(n);
		}
		
		return dtoService.convertToDTO(n);
	}

	@Override
	public List<CommentsDTO> findCommentsByEntryId(long entryId) {
		List<CommentsDTO> dtos = new ArrayList<>();
		List<Comments> comments = commentsRepository.findByEntry_entryId(entryId);

		comments.forEach(comment -> dtos.add(dtoService.convertToDTO(comment)));

		return dtos;
	}

	@Override
	public CommentsDTO addComment(CommentsDTO commentsDTO) {
		Comments c = dtoService.convertToModel(commentsDTO);
		c.setCommentDate(new Date());
		c = commentsRepository.save(c);

		return dtoService.convertToDTO(c);
	}

}
