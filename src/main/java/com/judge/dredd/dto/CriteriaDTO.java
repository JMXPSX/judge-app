package com.judge.dredd.dto;

public class CriteriaDTO{

	private long criteriaId;
	
	private String criteriaName;
	private String criteriaDescription;
	private Integer minValue;
	private Integer maxValue;
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
	public Integer getMinValue() {
		return minValue;
	}
	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}
	public Integer getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	
	@Override
	public String toString() {
		return "CriteriaDTO [criteriaId=" + criteriaId + ", criteriaName=" + criteriaName + ", criteriaDescription="
				+ criteriaDescription + ", minValue=" + minValue + ", maxValue=" + maxValue + ", eventId=" + eventId
				+ "]";
	}	
	
}
