package com.vote.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vote.model.Participant;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long>{

	
}
