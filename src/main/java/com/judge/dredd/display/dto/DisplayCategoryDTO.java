package com.judge.dredd.display.dto;

public class DisplayCategoryDTO {

	private long categoryId;
	private String categoryName;
	private int minValue;
	private int maxValue;
	private DisplayScoreDTO score;
	
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
