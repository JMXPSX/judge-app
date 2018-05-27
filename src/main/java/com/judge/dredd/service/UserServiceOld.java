package com.judge.dredd.service;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import com.judge.dredd.dto.LoginDTO;
import com.judge.dredd.model.AppUser;
import com.judge.dredd.repository.AppUserRepository;

//converted to spring jwt
//@Service
public class UserServiceOld {
	
	@Inject
	AppUserRepository userRepository;
	
	public LoginDTO login(String username, String password){
		LoginDTO loginDTO = new LoginDTO();
		
		AppUser user = userRepository.findByUsernameAndPassword(username, password);
		//int rows affected = update user set token = tempToken, set expiry =sysdate+24hrs where user=user and pass=pass
		
		if(null != user){
			Date now = new Date();
			String token = String.valueOf(now.getTime());
			Date expiry = addHours(now, 24);
			userRepository.save(user);
			loginDTO.setUserId(user.getUserId());
			loginDTO.setToken(token);
			loginDTO.setMessage("login success");
		}else{
			loginDTO.setMessage("invalid user or pass");
		}
		
		return loginDTO;
	}
	
	public Date addHours(Date date, int hours){
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.HOUR_OF_DAY, hours);
	    return cal.getTime();
	}
	
	public boolean isTokenValid(){
		return false;
	}
}
