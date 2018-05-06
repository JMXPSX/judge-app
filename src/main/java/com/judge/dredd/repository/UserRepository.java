package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.SystemUser;

public interface UserRepository extends CrudRepository<SystemUser, Long>{

	@Query(value = "select u.* from user u where u.username = ?1 and u.password =?2", nativeQuery = true)
	public SystemUser findByUsernameAndPassword(String username, String password);
	
	public SystemUser findByUsername(String username);
	
	public List<SystemUser> findByUserType(int userType);
	
}
