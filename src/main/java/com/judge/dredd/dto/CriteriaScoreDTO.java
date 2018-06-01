package com.judge.dredd.dto;

public class CriteriaScoreDTO {

	private long criteriaId;
	private String criteriaName;
	private String criteriaDescription;
	private double score;
	private long scoreId;
	
	public long getCriteriaId() {
		return criteriaId;
	}
	public void setCriteriaId(long criteriaId) {
		this.criteriaId = criteriaId;
	}
	public String getCriteriaName() {
		return criteriaName;
	}
	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}
	public String getCriteriaDescription() {
		return criteriaDescription;
	}
	public void setCriteriaDescription(String criteriaDescription) {
		this.criteriaDescription = criteriaDescription;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public long getScoreId() {
		return scoreId;
	}
	public void setScoreId(long scoreId) {
		this.scoreId = scoreId;
	}
	
	
}
