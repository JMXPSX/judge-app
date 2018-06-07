package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.CriteriaScoreDTO;
import com.judge.dredd.dto.ScoreDTO;

public interface ScoreService {

	ScoreDTO getOne(long id);
	ScoreDTO save(ScoreDTO scoreDTO);
	void delete(long id);
	List<ScoreDTO> getAll();
	void setScore();
	String updateScore(ScoreDTO scoreDTO);
	
	
	public ScoreDTO getScoresByEventIdAndEntryIdAndJudgeIdAndCriteriaId(long eventId, long entryId, long judgeId, long criteriaId);
	public List<ScoreDTO> getScoresByEventIdAndJudgeId(long eventId, long judgeId);
	public List<ScoreDTO> getFinalizedScoresByEventId(long eventId);
	public List<ScoreDTO> getScoreByEventIdAndCategoryIdAndEntryId(long eventId, long categoryId, long entryId);
	public List<ScoreDTO> getScoreByEventIdAndCategoryIdAndEntryIdAndAppUserId(long eventId, long categoryId, long entryId, long appUserId);
	
	public List<ScoreDTO> getScoreByEventIdAndEntryId(long eventId, long entryId);
	public ScoreDTO getScoreByEventIdAndEntryIdAndAppUserId(long eventId, long entryId, long appUserId);
	public List<ScoreDTO> getScoresByEventIdAndAppUserId(long eventId, long appUserId);
	public List<ScoreDTO> getAllScoresForSponsor(long eventId);
	
	public String doneEntries(long entryId, long judgeId);
}
