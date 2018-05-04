package com.judge.dredd.dto;

import java.util.Date;

public class TokenDTO {

	private String token;
	
	private Date tokenExpiry;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpiry() {
		return tokenExpiry;
	}

	public void setTokenExpiry(Date tokenExpiry) {
		this.tokenExpiry = tokenExpiry;
	}
	
	
}
