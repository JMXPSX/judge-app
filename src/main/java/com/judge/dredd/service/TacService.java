package com.judge.dredd.service;

import org.springframework.stereotype.Service;

@Service
public class TacService {
	
	public boolean updateTac (boolean tacFlag) {
		
		return (tacFlag)?true:false;	
		
	};		

}
