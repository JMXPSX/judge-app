package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.UserDTO;

public interface AppUserService {
	UserDTO getOne(long id);
	UserDTO save(UserDTO scoreDTO);
	UserDTO pwResetFlag(UserDTO scoreDTO);
	void delete(long id);
	List<UserDTO> getAll();
	List<UserDTO> getAllByUserType(int userType);
	UserDTO login(String username, String password);
}
