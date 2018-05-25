package com.judge.dredd.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.CategoryDTO;
import com.judge.dredd.dto.CommentsDTO;
import com.judge.dredd.dto.CriteriaDTO;
import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.dto.EventDTO;
import com.judge.dredd.dto.MemberDTO;
import com.judge.dredd.dto.ScoreDTO;
import com.judge.dredd.dto.UserDTO;
import com.judge.dredd.model.AppUser;
import com.judge.dredd.model.Category;
import com.judge.dredd.model.Comments;
import com.judge.dredd.model.Criteria;
import com.judge.dredd.model.Entry;
import com.judge.dredd.model.Event;
import com.judge.dredd.model.Member;
import com.judge.dredd.model.Score;
import com.judge.dredd.service.DtoService;

@Service
public class DtoServiceImpl implements DtoService {

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Criteria convertToModel(CriteriaDTO criteriaDTO) {
		return this.modelMapper.map(criteriaDTO, Criteria.class);
	}

	@Override
	public CriteriaDTO convertToDTO(Criteria criteria) {
		return this.modelMapper.map(criteria, CriteriaDTO.class);
	}

	@Override
	public Entry convertToModel(EntryDTO entryDTO) {
		return this.modelMapper.map(entryDTO, Entry.class);
	}

	@Override
	public EntryDTO convertToDTO(Entry entry) {
		return this.modelMapper.map(entry, EntryDTO.class);
	}

	@Override
	public Score convertToModel(ScoreDTO eventDetailDTO) {
		return this.modelMapper.map(eventDetailDTO, Score.class);
	}

	@Override
	public ScoreDTO convertToDTO(Score score) {
		return this.modelMapper.map(score, ScoreDTO.class);
	}

	@Override
	public AppUser convertToModel(UserDTO userDTO) {
		return this.modelMapper.map(userDTO, AppUser.class);
	}

	@Override
	public UserDTO convertToDTO(AppUser systemUser) {
		return this.modelMapper.map(systemUser, UserDTO.class);
	}

	@Override
	public Member convertToModel(MemberDTO memberDTO) {
		return this.modelMapper.map(memberDTO, Member.class);
	}

	@Override
	public MemberDTO convertToDTO(Member member) {
		return this.modelMapper.map(member, MemberDTO.class);
	}

	@Override
	public Event convertToModel(EventDTO eventDTO) {
		return this.modelMapper.map(eventDTO, Event.class);
	}

	@Override
	public EventDTO convertToDTO(Event event) {
		return this.modelMapper.map(event, EventDTO.class);
	}

	@Override
	public Category convertToModel(CategoryDTO categoryDTO) {
		return this.modelMapper.map(categoryDTO, Category.class);
	}

	@Override
	public CategoryDTO convertToDTO(Category category) {
		return this.modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public Comments convertToModel(CommentsDTO commentsDTO) {
		return this.modelMapper.map(commentsDTO, Comments.class);
	}

	@Override
	public CommentsDTO convertToDTO(Comments comment) {
		return this.modelMapper.map(comment, CommentsDTO.class);
	}

}
