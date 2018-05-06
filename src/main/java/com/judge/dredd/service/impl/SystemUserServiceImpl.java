package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.judge.dredd.dto.UserDTO;
import com.judge.dredd.model.SystemUser;
import com.judge.dredd.repository.UserRepository;
import com.judge.dredd.service.DtoService;
import com.judge.dredd.service.SystemUserService;

@Service
public class SystemUserServiceImpl implements SystemUserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DtoService dtoService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public UserDTO getOne(long id) {
		SystemUser obj = userRepository.findById(id).get();
		return dtoService.convertToDTO(obj);
	}

	@Override
	public UserDTO save(UserDTO userDTO) {
		SystemUser obj = dtoService.convertToModel(userDTO);
		
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
		List<SystemUser> obj = Lists.newArrayList(userRepository.findAll());
		List<UserDTO> objDTo = new ArrayList<>();

		obj.forEach(i -> {
			objDTo.add(dtoService.convertToDTO(i));
		});

		return objDTo;
	}

	@Override
	public List<UserDTO> getAllByUserType(int userType) {
		List<SystemUser> obj = Lists.newArrayList(userRepository.findByUserType(userType));
		List<UserDTO> objDTo = new ArrayList<>();

		obj.forEach(i -> {
			objDTo.add(dtoService.convertToDTO(i));
		});

		return objDTo;
	}

}
