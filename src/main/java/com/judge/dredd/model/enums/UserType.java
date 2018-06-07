package com.judge.dredd.model.enums;

public enum UserType {
	APP_JUDGE(1),
	APP_ADMIN(2),
	APP_SPONSOR(3),
	APP_ACADEME(4);
	
	private final int id;
	UserType(int id) { this.id = id; }
    public int getType() { return id; }
}
