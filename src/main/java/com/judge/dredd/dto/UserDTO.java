package com.judge.dredd.dto;

import java.util.Date;

public class UserDTO{
	
	private long id;	
	private String userName;
	private String password;
	private int type;
	private boolean isTNC;
	private boolean isPWReset;
	private Date lastUpdate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isTNC() {
		return isTNC;
	}
	public void setTNC(boolean isTNC) {
		this.isTNC = isTNC;
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
	
	
}
