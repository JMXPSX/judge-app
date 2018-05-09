package com.judge.dredd.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.CriteriaDTO;
import com.judge.dredd.dto.EntryCriteriaDTO;
import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.dto.EventDetailDTO;
import com.judge.dredd.dto.ScoreDTO;
import com.judge.dredd.dto.UserDTO;
import com.judge.dredd.model.Criteria;
import com.judge.dredd.model.Entry;
import com.judge.dredd.model.EntryCriteria;
import com.judge.dredd.model.EventDetail;
import com.judge.dredd.model.Score;
import com.judge.dredd.model.SystemUser;
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
	public EventDetail convertToModel(EventDetailDTO eventDetailDTO) {
		return this.modelMapper.map(eventDetailDTO, EventDetail.class);
	}

	@Override
	public EventDetailDTO convertToDTO(EventDetail eventDetail) {
		return this.modelMapper.map(eventDetail, EventDetailDTO.class);
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
	public SystemUser convertToModel(UserDTO userDTO) {
		return this.modelMapper.map(userDTO, SystemUser.class);
	}

	@Override
	public UserDTO convertToDTO(SystemUser systemUser) {
		return this.modelMapper.map(systemUser, UserDTO.class);
	}

	@Override
	public EntryCriteriaDTO toDTO(EntryCriteria entryCriteria) {
		return this.modelMapper.map(entryCriteria, EntryCriteriaDTO.class);
	}

	@Override
	public EntryCriteria toModel(EntryCriteriaDTO entryCriteriaDTO) {
		return this.modelMapper.map(entryCriteriaDTO, EntryCriteria.class);
	}



}
