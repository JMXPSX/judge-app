package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.EventDetailCriteria;

public interface EventDetailCriteriaRepository extends CrudRepository<EventDetailCriteria, Long>{

	@Query(value = "select e.* from eventdetailcriteria e where e.event_detail_id = ?1", nativeQuery = true)
	public List<EventDetailCriteria> findByEventDetailId(long eventId);
}
