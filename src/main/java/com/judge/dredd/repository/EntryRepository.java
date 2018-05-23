package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Entry;

public interface EntryRepository extends CrudRepository<Entry, Long>{

	List<Entry> findAllByEventId(long eventId);
	List<Entry> findByEntryId(long entryId);
	
}
