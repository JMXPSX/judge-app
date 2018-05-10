package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.display.dto.DisplayEventDTO;
import com.judge.dredd.dto.ScoreDTO;

public interface ScoreService {

	ScoreDTO getOne(long id);
	ScoreDTO save(ScoreDTO scoreDTO);
	void delete(long id);
	List<ScoreDTO> getAll();
	String finalizeScore(long eventId, long judgeId);
	void setScore();
	String updateScore(ScoreDTO scoreDTO);
	
	public String setDone(long eventId, long entryId, long judgeId);
	
	public ScoreDTO getScoresByEventIdAndEntryIdAndJudgeIdAndCriteriaId(long eventId, long entryId, long judgeId, long criteriaId);
	public ScoreDTO getScoresByEventIdAndEntryIdAndJudgeId(long eventId, long entryId, long judgeId);
	public List<ScoreDTO> getScoresByEventIdAndJudgeId(long eventId, long judgeId);
	public List<ScoreDTO> getFinalizedScoresByEventId(long eventId);
	
}
