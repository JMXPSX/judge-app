package com.judge.dredd.dto;

public class CriteriaDTO{

	private long criteriaId;
	
	private String criteriaName;
	private String criteriaDescription;
	private int minValue;
	private int maxValue;
	private long eventId;
	
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
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	
	
}
