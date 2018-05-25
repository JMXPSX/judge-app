package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Entry;

public interface EntryRepository extends CrudRepository<Entry, Long>{

	List<Entry> findAllByEventId(long eventId);
	List<Entry> findByEntryId(long entryId);
	
//	@Query(value = "select e.* from event e where e.event_id = ?1 and e.category_id = ?2 and e.user", nativeQuery = true)
	List<Entry> findByEventIdAndCategory_idAndJudges_id(long eventId, long categoryId, long id);
	
}
