package com.judge.dredd.dto;

import com.judge.dredd.model.Score;

public class ScoreDTO extends TokenDTO{

	private Long scoreId;
	
	//the event
	private int eventId;
	
	// the entry
	private int entryId;
	
	//the judge
	private int judgeId;
	
	private int criteriaId;
	
	private int score;
	
	// done scoring
	private boolean isDone;
	
	// judge finalized
	private boolean isFinal;

	public Long getScoreId() {
		return scoreId;
	}

	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public int getJudgeId() {
		return judgeId;
	}

	public void setJudgeId(int judgeId) {
		this.judgeId = judgeId;
	}

	public int getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(int criteriaId) {
		this.criteriaId = criteriaId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	
	public ScoreDTO toDTO(Score s){
		ScoreDTO dto = new ScoreDTO();
		dto.setScoreId(s.getScoreId());
		dto.setEventId(s.getEntryId());
		dto.setEntryId(s.getEntryId());
		dto.setJudgeId(s.getJudgeId());
		dto.setCriteriaId(s.getCriteriaId());
		dto.setScore(s.getScore());
		dto.setDone(s.isDone());
		dto.setFinal(s.isFinal());
		return dto;
	}
	
}