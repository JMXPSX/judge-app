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
	public String finalizeScore(long eventId, long judgeId) {
		String message = "message: ";
		
		//get all scores
		List<Score> scores = scoreRepository.findScoreByEventIdAndJudgeId(eventId, judgeId);
		// all scores should have done flag = true
//		if(scores.size() == scores.stream().filter(s -> s.isDone() == true).count()){
			// finalize each one
			scores.forEach(s -> {
//				s.setFinal(true);
				scoreRepository.save(s);
				});
			message += " done";
//		}else{
//			message += " some items are not yet done";
//		}
		
		return message;
	}

	@Override
	public void setScore() {
		// TODO Auto-generated method stub

	}

	@Override
	public String updateScore(ScoreDTO scoreDTO) {
		String message = "message: success";
		
		Score score = scoreRepository.findById(scoreDTO.getScoreId()).get();
		
		score.setScore(scoreDTO.getScore());
		score.setDone(scoreDTO.isDone());
		scoreRepository.save(score);
		
		return message;

	}

	@Override
	public List<ScoreDTO> getScoresByEventIdAndJudgeId(long eventId, long judgeId) {
		List<ScoreDTO> scoreDTOs = new ArrayList<>();
		
		List<Score> scores = scoreRepository.findScoreByEventIdAndJudgeId(eventId, judgeId);
		scores.forEach(score -> scoreDTOs.add(new ScoreDTO().toDTO(score)));
		
		return scoreDTOs;
	}

	@Override
	public List<ScoreDTO> getFinalizedScoresByEventId(long eventId) {
		List<ScoreDTO> scoreDTOs = new ArrayList<>();
		
		List<Score> scores = scoreRepository.findFinalScoreByEventId(eventId);
		scores.forEach(score -> scoreDTOs.add(new ScoreDTO().toDTO(score)));
		
		return scoreDTOs;
	}

	@Override
	public ScoreDTO getScoresByEventIdAndEntryIdAndJudgeIdAndCriteriaId(long eventId, long entryId, long judgeId, long criteriaId) {
		
		System.out.println("select s.* from dredd.score s where s.event_detail_id = "+eventId+" and s.entry_id = "+entryId+" and s.judge_id = "+judgeId+" and s.criteria_id = "+criteriaId);
		
		Score score = scoreRepository.findScoreByEventIdAndEntryIdAndJudgeIdAndCriteriaId(eventId, entryId, judgeId, criteriaId);
		
		if(null != score){
			return dtoService.convertToDTO(score);
		}else{
			return null;
		}
		
		
	}
	
	@Override
	public ScoreDTO getScoresByEventIdAndEntryIdAndJudgeId(long eventId, long entryId, long judgeId) {
//		System.out.println("select s.* from dredd.score s where s.event_detail_id = "+eventId+" and s.entry_id = "+entryId+" and s.judge_id = "+judgeId);
		List<Score> scores = scoreRepository.findScoreByEventIdAndEntryIdAndJudgeId(eventId, entryId, judgeId);
		return null;//dtoService.convertToDTO(score);
	}

	@Override
	public String setDone(long eventId, long entryId, long judgeId) {
		List<Score> scores = scoreRepository.findScoreByEventIdAndEntryIdAndJudgeId(eventId, entryId, judgeId);
		scores.forEach(s ->{
			s.setDone(true);
			scoreRepository.save(s);
		});
		return "message: done";
	}
	
}
