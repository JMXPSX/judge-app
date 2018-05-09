package com.judge.dredd.display.dto;

public class DisplayCriteriaDTO {

	private long criteriaId;
	private String criteriaName;
	private int minValue;
	private int maxValue;
	private DisplayScoreDTO score;
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
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	public DisplayScoreDTO getScore() {
		return score;
	}
	public void setScore(DisplayScoreDTO score) {
		this.score = score;
	}
	
	
	
}
