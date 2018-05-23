package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long>{

	@Query(value = "select u.* from user u where u.username = ?1 and u.password =?2", nativeQuery = true)
	public AppUser findByUsernameAndPassword(String username, String password);
	
	public AppUser findByUsername(String username);
	
	public List<AppUser> findByUserType(int userType);
	
}
