package com.judge.dredd.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.judge.dredd.controller.ScoreController;
import com.judge.dredd.dto.CriteriaScoreDTO;
import com.judge.dredd.dto.ScoreDTO;
import com.judge.dredd.model.AppUser;
import com.judge.dredd.model.Criteria;
import com.judge.dredd.model.Entry;
import com.judge.dredd.model.Event;
import com.judge.dredd.model.Score;
import com.judge.dredd.model.Tabulator;
import com.judge.dredd.repository.AppUserRepository;
import com.judge.dredd.repository.CriteriaRepository;
import com.judge.dredd.repository.EntryRepository;
import com.judge.dredd.repository.EventRepository;
import com.judge.dredd.repository.ScoreRepository;
import com.judge.dredd.repository.TabulatorRepository;
import com.judge.dredd.service.DtoService;
import com.judge.dredd.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Autowired
	private CriteriaRepository criteriaRepository;

	@Autowired
	private TabulatorRepository tabulatorRepository;
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private DtoService dtoService;
	
	
	@Override
	public ScoreDTO getOne(long id) {
		Score obj = scoreRepository.findById(id).get();
		
		return dtoService.convertToDTO(obj);
	}

	@Override
	public ScoreDTO save(ScoreDTO scoreDTO) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		Tabulator tabulator = new Tabulator();
				
		Entry entry = entryRepository.findById(scoreDTO.getEntryId()).get();		
		Event event = eventRepository.findById(scoreDTO.getEventId()).get();
		AppUser judge = appUserRepository.findById(scoreDTO.getJudgeId()).get();
		
		tabulator.setEntry(entry);;
		tabulator.setEvent(event);
		tabulator.setJudge(judge);
		tabulator.setFinal(false);
		tabulator.setCreatedDate(now);
		tabulator.setUpdatedDate(now);
		
		final Tabulator tab = tabulatorRepository.save(tabulator);
		
		List<Score> scores = new ArrayList<>();
		
		scoreDTO.getScores().forEach(s -> {
			System.out.println("loop scores");
			Score score = new Score();
			Criteria criteria = criteriaRepository.findById(s.getCriteriaId()).get();
			
			score.setCriteria(criteria);
			score.setCreatedDate(now);
			score.setDone(false);
			score.setScore(s.getScore());
			score.setUpdatedDate(now);
			score.setTabulator(tab);
			scores.add(score);
			
		});
		scoreRepository.saveAll(scores);
		
		

		return scoreDTO;
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
		return null;
	}

	@Override
	public List<ScoreDTO> getScoresByEventIdAndJudgeId(long eventId, long judgeId) {
		
		
		return null;
	}

	@Override
	public List<ScoreDTO> getFinalizedScoresByEventId(long eventId) {
		
		return null;
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

	@Override
	public List<ScoreDTO> getScoreByEventIdAndCategoryIdAndEntryId(long eventId, long categoryId, long entryId) {
//		scoreRepository
		return null;
	}

	@Override
	public List<ScoreDTO> getScoreByEventIdAndCategoryIdAndEntryIdAndAppUserId(long eventId, long categoryId,
			long entryId, long appUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreDTO getScoreByEventIdAndEntryId(long eventId, long entryId) {
		List<CriteriaScoreDTO> dtos = new ArrayList<>();
		List<Score> scores = scoreRepository.findByCriteria_Event_idAndTabulator_Entry_entryId(eventId, entryId);
		scores.forEach(score -> dtos.add(dtoService.convertToMixDTO(score)));
		
		Tabulator tab = tabulatorRepository.findByEvent_IdAndEntry_entryId(eventId, entryId);
		ScoreDTO dto = new ScoreDTO();
		dto.setScores(dtos);
		dto.setEntryId(tab.getEntry().getEntryId());
		dto.setEventId(tab.getEvent().getId());
		dto.setJudgeId(tab.getJudge().getUserId());
		dto.setTabulatorId(tab.getId());
		dto.setFinal(tab.isFinal());
		dto.setCategoryId(tab.getEntry().getCategory().getId());
		
		return dto;
	}

	@Override
	public List<ScoreDTO> getScoreByEventIdAndEntryIdAndAppUserId(long eventId, long entryId, long appUserId) {
		List<ScoreDTO> dtos = new ArrayList<>();
		List<Score> scores = scoreRepository.findByCriteria_Event_idAndTabulator_Entry_entryIdAndTabulator_Judge_userId(eventId, entryId, appUserId);
		scores.forEach(score -> dtos.add(dtoService.convertToDTO(score)));
		return dtos;
	}
	
}
