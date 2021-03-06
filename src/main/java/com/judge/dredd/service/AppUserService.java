package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.UserDTO;
import com.judge.dredd.dto.UserTypeDTO;

public interface AppUserService {
	UserDTO getOne(long id);
	UserDTO save(UserDTO scoreDTO);
	UserDTO pwResetFlag(long userId);
	void delete(long id);
	List<UserDTO> getAll();
	List<UserDTO> getAllByUserType(int userType);
	List<UserTypeDTO> getUserTypes();
	UserDTO login(String username, String password);
	UserDTO setNewPassword(UserDTO scoreDTO);
}
