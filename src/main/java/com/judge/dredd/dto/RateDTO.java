package com.judge.dredd.dto;

public class RateDTO {

	private long rateId;
	private double rating;
	private double max;
	private double min;
	private long eventId;
	private long categoryId;
	private long judgeId;
	public long getRateId() {
		return rateId;
	}
	public void setRateId(long rateId) {
		this.rateId = rateId;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
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
	
	
}
