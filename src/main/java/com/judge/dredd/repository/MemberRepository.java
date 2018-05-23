package com.judge.dredd.repository;

import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Member;

public interface MemberRepository extends CrudRepository<Member, Long>{

	
}
