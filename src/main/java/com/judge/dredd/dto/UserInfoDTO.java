package com.judge.dredd.dto;

public class UserInfoDTO{

	private long id;
	private String userName;
	private int type;
	private boolean isTNC;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isTNC() {
		return isTNC;
	}
	public void setTNC(boolean isTNC) {
		this.isTNC = isTNC;
	}
	
	
}
