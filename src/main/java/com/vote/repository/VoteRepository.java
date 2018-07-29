package com.vote.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vote.model.Vote;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long>{

	public List<Vote> findByParticipant_participantId(long participantId);
	
	public List<Vote> findByEventId(long eventId);
}
