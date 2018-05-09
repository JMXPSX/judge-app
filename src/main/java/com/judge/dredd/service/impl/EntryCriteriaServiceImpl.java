package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.EntryCriteriaDTO;
import com.judge.dredd.model.EntryCriteria;
import com.judge.dredd.repository.EntryCriteriaRepository;
import com.judge.dredd.service.DtoService;
import com.judge.dredd.service.EntryCriteriaService;

@Service
public class EntryCriteriaServiceImpl implements EntryCriteriaService {
	
	@Autowired
	EntryCriteriaRepository entryCriteriaRepository;
	
	@Autowired
	DtoService dtoService;

	@Override
	public List<EntryCriteriaDTO> getAllEntriesByEntryId(long entryId) {
		List<EntryCriteriaDTO> dtos = new ArrayList();
		List<EntryCriteria> ecs = entryCriteriaRepository.findByEntryId(entryId);
		for(EntryCriteria ec : ecs){
			dtos.add(dtoService.toDTO(ec));
		}
		return dtos;
	}

	
}
