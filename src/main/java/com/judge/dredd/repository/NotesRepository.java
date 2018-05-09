package com.judge.dredd.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Notes;

public interface NotesRepository extends CrudRepository<Notes, Long>{

	@Query(value = "select n.* from notes n where n.entry_id = ?1 and n.judge_id = ?2", nativeQuery = true)
	public Notes findByEntryIdAndJudgeId(long entryId, long judgeId);
}
