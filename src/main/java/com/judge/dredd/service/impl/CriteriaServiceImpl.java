package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.judge.dredd.dto.CriteriaDTO;
import com.judge.dredd.model.Criteria;
import com.judge.dredd.model.EventDetailCriteria;
import com.judge.dredd.repository.CriteriaRepository;
import com.judge.dredd.repository.EventDetailCriteriaRepository;
import com.judge.dredd.service.CriteriaService;
import com.judge.dredd.service.DtoService;

@Service
public class CriteriaServiceImpl implements CriteriaService {

	@Autowired
	private CriteriaRepository criteriaRepository;

	@Autowired
	private DtoService dtoService;
	
	@Autowired
	private EventDetailCriteriaRepository eventDetailCriteriaRepository;

	@Override
	public CriteriaDTO getOne(long id) {
		
		Criteria obj = criteriaRepository.findById(id).get();
		
		return dtoService.convertToDTO(obj);
	}

	@Override
	public CriteriaDTO save(CriteriaDTO criteriaDTO) {
		
		Criteria obj = dtoService.convertToModel(criteriaDTO);
		obj = criteriaRepository.save(obj);

		return dtoService.convertToDTO(obj);
	}

	@Override
	public void delete(long id) {
		
		criteriaRepository.deleteById(id);
		
	}

	@Override
	public List<CriteriaDTO> getAll() {

		List<Criteria> obj = Lists.newArrayList(criteriaRepository.findAll());
		List<CriteriaDTO> objDTo = new ArrayList<>();

		obj.forEach(i -> {
			objDTo.add(dtoService.convertToDTO(i));
		});

		return objDTo;
	}
	
	public List<CriteriaDTO> getByEventDetailId(long eventId){
		List<CriteriaDTO> criteria = new ArrayList();
		List<EventDetailCriteria> list = eventDetailCriteriaRepository.findByEventDetailId(eventId);
		list.forEach(e -> {
			Criteria c = criteriaRepository.findById(e.getCriteriaId()).get();
			criteria.add(dtoService.convertToDTO(c));
		});		
		return criteria;
	}

}
