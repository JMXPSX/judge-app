package com.judge.dredd.dto;

public class RateDTO {

	private long tabulatorId;
	private int rateValue;
	private long eventId;
	private long judgeId;
	private long entryId;
	
	public long getTabulatorId() {
		return tabulatorId;
	}
	public void setTabulatorId(long tabulatorId) {
		this.tabulatorId = tabulatorId;
	}
	public int getRateValue() {
		return rateValue;
	}
	public void setRateValue(int rateValue) {
		this.rateValue = rateValue;
	}
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public long getJudgeId() {
		return judgeId;
	}
	public void setJudgeId(long judgeId) {
		this.judgeId = judgeId;
	}
	public long getEntryId() {
		return entryId;
	}
	public void setEntryId(long entryId) {
		this.entryId = entryId;
	}
	
	
}
