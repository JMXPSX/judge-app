package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.UserDTO;

public interface SystemUserService {
	UserDTO getOne(long id);
	UserDTO save(UserDTO scoreDTO);
	void delete(long id);
	List<UserDTO> getAll();
	List<UserDTO> getAllByUserType(int userType);
	public UserDTO login(String username, String password);
}
