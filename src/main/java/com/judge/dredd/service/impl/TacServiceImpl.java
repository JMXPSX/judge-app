package com.judge.dredd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.UserDTO;
import com.judge.dredd.model.AppUser;
import com.judge.dredd.repository.AppUserRepository;
import com.judge.dredd.service.DtoService;
import com.judge.dredd.service.TacService;

@Service
public class TacServiceImpl implements TacService {
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private DtoService dtoService;

	@Override
	public UserDTO agreeTac(long userId) {
		
		AppUser appUser = appUserRepository.findById(userId).get();
		
		appUser.setWithTAC(true);
		
		appUser = appUserRepository.save(appUser);
		
		return dtoService.convertToDTO(appUser);
		
	}

}
