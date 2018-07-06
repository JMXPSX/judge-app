package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.RateDTO;
import com.judge.dredd.model.Tabulator;
import com.judge.dredd.repository.TabulatorRepository;
import com.judge.dredd.service.DtoService;
import com.judge.dredd.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private TabulatorRepository tabulatorRepository;
	
	@Autowired
	private DtoService dtoService;

	@Override
	public String update(RateDTO rateDTO) throws Exception {
		Tabulator t = tabulatorRepository.findById(rateDTO.getTabulatorId()).orElseThrow(() -> new Exception("Tabulator id "+rateDTO.getTabulatorId()+" not found"));
		t.setRateValue(rateDTO.getRateValue());
		tabulatorRepository.save(t);
		return "done";
	}

	@Override
	public RateDTO getRating(long eventId, long entryId, long appuserId) {
		Tabulator t = tabulatorRepository.findByEvent_IdAndEntry_entryIdAndJudge_userId(eventId, entryId, appuserId);
		RateDTO r = new RateDTO();
		r.setEventId(t.getEvent().getId());
		r.setJudgeId(t.getJudge().getUserId());
		r.setRateValue(t.getRateValue());
		r.setTabulatorId(t.getId());
		return r;
	}

	@Override
	public List<RateDTO> getRating(long eventId, long appuserId) {
		List<RateDTO> dtos = new ArrayList();
		List<Tabulator> tabulators = tabulatorRepository.findByEvent_IdAndJudge_userId(eventId, appuserId);
		tabulators.forEach(tabulator -> dtos.add(dtoService.convertToDTO(tabulator)));
		return dtos;
	}	

}
