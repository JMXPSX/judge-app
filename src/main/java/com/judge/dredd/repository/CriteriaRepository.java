package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Criteria;

public interface CriteriaRepository extends CrudRepository<Criteria, Long>{

	List<Criteria> findByEventId(long eventId);
}
