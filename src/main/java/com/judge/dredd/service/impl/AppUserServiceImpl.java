package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.judge.dredd.dto.UserDTO;
import com.judge.dredd.model.AppUser;
import com.judge.dredd.repository.AppUserRepository;
import com.judge.dredd.service.AppUserService;
import com.judge.dredd.service.DtoService;

@Service
public class AppUserServiceImpl implements AppUserService{

	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private DtoService dtoService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public UserDTO getOne(long id) {
		AppUser obj = userRepository.findById(id).get();
		return dtoService.convertToDTO(obj);
	}

	@Override
	public UserDTO save(UserDTO userDTO) {
		AppUser obj = dtoService.convertToModel(userDTO);
		
		String pass = obj.getPassword();
		
		obj.setPassword(bcrypt.encode(pass));
		
		obj = userRepository.save(obj);

		return dtoService.convertToDTO(obj);
	}

	@Override
	public void delete(long id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<UserDTO> getAll() {
		List<AppUser> obj = Lists.newArrayList(userRepository.findAll());
		List<UserDTO> objDTo = new ArrayList<>();

		obj.forEach(i -> {
			objDTo.add(dtoService.convertToDTO(i));
		});

		return objDTo;
	}

	@Override
	public List<UserDTO> getAllByUserType(int userType) {
		List<AppUser> obj = Lists.newArrayList(userRepository.findByUserType(userType));
		List<UserDTO> objDTo = new ArrayList<>();

		obj.forEach(i -> {
			objDTo.add(dtoService.convertToDTO(i));
		});

		return objDTo;
	}

	@Override
	public UserDTO login(String username, String password) {
		UserDTO u = new UserDTO();
		AppUser user = userRepository.findByUsernameAndPassword(username, password);
		if(null != user){
			userRepository.save(user);
			u.setId(user.getId());
			u.setUserType(user.getUserType());
			u.setUserName(user.getUsername());
			u.setPassword(user.getPassword());
		}
		
		return u;
	}

}
