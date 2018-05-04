package com.judge.dredd.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	@Query(value = "select u.* from user u where u.username = ?1 and u.password =?2", nativeQuery = true)
	public User findByUsernameAndPassword(String username, String password);
}
