package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.judge.dredd.dto.ScoreDTO;
import com.judge.dredd.model.Score;
import com.judge.dredd.repository.ScoreRepository;
import com.judge.dredd.service.DtoService;
import com.judge.dredd.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {
	
	@Autowired
	private ScoreRepository scoreRepository;

	@Autowired
	private DtoService dtoService;
	
	
	@Override
	public ScoreDTO getOne(long id) {
		Score obj = scoreRepository.findById(id).get();
		
		return dtoService.convertToDTO(obj);
	}

	@Override
	public ScoreDTO save(ScoreDTO scoreDTO) {
		
		Score obj = dtoService.convertToModel(scoreDTO);
		obj = scoreRepository.save(obj);

		return dtoService.convertToDTO(obj);
	}

	@Override
	public void delete(long id) {
		scoreRepository.deleteById(id);
	}

	@Override
	public List<ScoreDTO> getAll() {
		List<Score> obj = Lists.newArrayList(scoreRepository.findAll());
		List<ScoreDTO> objDTo = new ArrayList<>();

		obj.forEach(i -> {
			objDTo.add(dtoService.convertToDTO(i));
		});

		return objDTo;
	}

	@Override
	public void finalizeScore() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setScore() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateScore() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ScoreDTO> getScoresByEventIdAndJudgeId(int eventId, int judgeId) {
		List<ScoreDTO> scoreDTOs = new ArrayList<>();
		
		List<Score> scores = scoreRepository.findScoreByEventIdAndJudgeId(eventId, judgeId);
		scores.forEach(score -> scoreDTOs.add(new ScoreDTO().toDTO(score)));
		
		return scoreDTOs;
	}

	@Override
	public List<ScoreDTO> getFinalizedScoresByEventId(int eventId) {
		List<ScoreDTO> scoreDTOs = new ArrayList<>();
		
		List<Score> scores = scoreRepository.findFinalScoreByEventId(eventId);
		scores.forEach(score -> scoreDTOs.add(new ScoreDTO().toDTO(score)));
		
		return scoreDTOs;
	}

}
