package com.judge.dredd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.RateDTO;
import com.judge.dredd.model.Tabulator;
import com.judge.dredd.repository.TabulatorRepository;
import com.judge.dredd.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private TabulatorRepository tabulatorRepository;

	@Override
	public String update(RateDTO rateDTO) {
		Tabulator t = tabulatorRepository.findById(rateDTO.getTabulatorId()).get();
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

}
