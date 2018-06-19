package com.judge.dredd.dto;

import java.util.List;

public class ScoreDTO {

	private long tabulatorId;

	// the event
	private long eventId;

	// the entry
	private long entryId;
		
	private long criteriaId;

	private long categoryId;

	// the judge
	private long judgeId;

	private List<CriteriaScoreDTO> scores;
	
	private long score;

	// judge finalized
	private boolean isFinal;

	public long getTabulatorId() {
		return tabulatorId;
	}

	public void setTabulatorId(long tabulatorId) {
		this.tabulatorId = tabulatorId;
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

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getJudgeId() {
		return judgeId;
	}

	public void setJudgeId(long judgeId) {
		this.judgeId = judgeId;
	}

	public List<CriteriaScoreDTO> getScores() {
		return scores;
	}

	public void setScores(List<CriteriaScoreDTO> scores) {
		this.scores = scores;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}	

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public long getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(long criteriaId) {
		this.criteriaId = criteriaId;
	}

	@Override
	public String toString() {
		return "ScoreDTO [tabulatorId=" + tabulatorId + ", eventId=" + eventId + ", entryId=" + entryId
				+ ", criteriaId=" + criteriaId + ", categoryId=" + categoryId + ", judgeId=" + judgeId + ", scores="
				+ scores + ", score=" + score + ", isFinal=" + isFinal + "]";
	}

}