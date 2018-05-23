package com.judge.dredd.dto;

import com.judge.dredd.model.Score;

public class ScoreDTO {

	private Long scoreId;
	
	//the event
	private long eventId;
	
	// the entry
	private long entryId;
	
	//the judge
	private long judgeId;
	
	private long criteriaId;
	
	private double score;
	
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

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getEntryId() {
		return entryId;
	}

	public void setEntryId(long entryId) {
		this.entryId = entryId;
	}

	public long getJudgeId() {
		return judgeId;
	}

	public void setJudgeId(long judgeId) {
		this.judgeId = judgeId;
	}

	public long getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(long criteriaId) {
		this.criteriaId = criteriaId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
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
//		dto.setScoreId(s.getScoreId());
//		dto.setEventId(s.getEvent().getId());
//		dto.setEntryId(s.getEntry().getEntryId());
//		dto.setJudgeId(s.getJudge().getId());
//		dto.setCriteriaId(s.getCriteria().getCriteriaId());
//		dto.setScore(s.getScore());
//		dto.setDone(s.isDone());
//		dto.setFinal(s.isFinal());
		return dto;
	}
	
}