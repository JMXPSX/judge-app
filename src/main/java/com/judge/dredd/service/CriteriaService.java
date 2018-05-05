package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.CriteriaDTO;

public interface CriteriaService {
	CriteriaDTO getOne(long id);
	CriteriaDTO save(CriteriaDTO criteriaDTO);
	void delete(long id);
	List<CriteriaDTO> getAll();
}
