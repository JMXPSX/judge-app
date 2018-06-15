package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.CommentsDTO;
import com.judge.dredd.dto.UserDTO;
import com.judge.dredd.model.Comments;
import com.judge.dredd.repository.CommentsRepository;
import com.judge.dredd.service.AppUserService;
import com.judge.dredd.service.CommentsService;
import com.judge.dredd.service.DtoService;

@Service
public class CommentsServiceImpl implements CommentsService {
	
	@Autowired
	private CommentsRepository commentsRepository;
	
	@Autowired
	private DtoService dtoService;
	
	@Autowired
	private AppUserService appUserService;
	
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
		Comments c;
		
		//check if existing
		List<Comments> exisitngComments = commentsRepository.findByEntry_entryIdAndAppUser_userIdAndComment(commentsDTO.getEntryId(), commentsDTO.getUserId(), commentsDTO.getComment());
		if(0 == exisitngComments.size()){
			//if not, add it
			c = dtoService.convertToModel(commentsDTO);
			UserDTO user = appUserService.getOne(commentsDTO.getUserId());
			
			c.setCommentDate(new Date());
			
			//user comment type = user type
			c.setUserCommentType(user.getUserType());			
			c = commentsRepository.save(c);
		}else{
			//if exists, return it
			c = exisitngComments.get(0);
		}

		return dtoService.convertToDTO(c);
	}

}
