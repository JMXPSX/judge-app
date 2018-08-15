package com.vote.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vote.model.ProxyVote;

@Repository
public interface ProxyVoteRepository extends CrudRepository<ProxyVote, Long>{
	
}
