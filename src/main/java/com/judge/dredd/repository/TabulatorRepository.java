package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Tabulator;

public interface TabulatorRepository extends CrudRepository<Tabulator, Long> {

	Tabulator findByEvent_IdAndEntry_entryId(long eventId, long entryId);
			
	@Query(value = "select t.* from tabulator t where t.entry_id = ?1 and t.user_id = ?2", nativeQuery = true)
	public Tabulator findByEntry_IdAndJudge_userId(long entryId, long userId);
	
	public Tabulator findByEvent_IdAndEntry_entryIdAndJudge_userId(long eventId, long entryId, long userId);
	
	public List<Tabulator> findByEvent_IdAndJudge_userId(long eventId, long userId);
}
