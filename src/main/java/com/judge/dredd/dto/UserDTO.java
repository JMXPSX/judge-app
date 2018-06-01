package com.judge.dredd.dto;

import java.util.Date;

public class UserDTO{
	private long userId;
	private String username;
	private String password;
	private int userType;
	private boolean isTAC;
	private boolean isPWReset;
	private Date lastUpdate;
	private String email;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long id) {
		this.userId = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public boolean isTAC() {
		return isTAC;
	}
	public void setTAC(boolean isTAC) {
		this.isTAC = isTAC;
	}
	public boolean isPWReset() {
		return isPWReset;
	}
	public void setPWReset(boolean isPWReset) {
		this.isPWReset = isPWReset;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	
}
