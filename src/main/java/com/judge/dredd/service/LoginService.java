package com.judge.dredd.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.judge.dredd.dto.LoginDTO;

@Service
public class LoginService {
	
	public LoginDTO login(String user, String pass){
		LoginDTO loginDTO = new LoginDTO();
		String tempToken = String.valueOf(new Date().getTime());
		int rowsAffected = 0;
		
		//int rows affected = update user set token = tempToken, set expiry =sysdate+24hrs where user=user and pass=pass
		
		if(rowsAffected > 0){
			loginDTO.setToken(tempToken);
			loginDTO.setMessage("login success");
		}else{
			loginDTO.setMessage("invalid user or pass");
		}
		
		return loginDTO;
	}
}
