package com.judge.dredd.dto;

public class UserDTO{
	
	private long id;
	
	private String username;
	private String password;
	private int userType;
	private boolean withTAC;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public boolean isWithTAC() {
		return withTAC;
	}
	public void setWithTAC(boolean withTAC) {
		this.withTAC = withTAC;
	}
	
	
}
