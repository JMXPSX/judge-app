package com.judge.dredd.model.enums;

public enum UserType {
	APP_ADMIN(1),
	APP_JUDGE(2),
	APP_SPONSOR(3),
	APP_ACADEME(4);
	
	private final int id;
	UserType(int id) { this.id = id; }
    public int getId() { return id; }
    
    public static UserType getUserTypeById(int id) {
    	
    	for(UserType userType : values()) {
    		if(userType.getId() == id) {
    			return userType;
    		}
    		
		}
    	return null;
    	
    }
}

