package com.judge.dredd.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Comments;

public interface CommentsRepository extends CrudRepository<Comments, Long>{

	@Query(value = "select n.* from comments n where n.entry_id = ?1 and n.judge_id = ?2", nativeQuery = true)
	public Comments findByEntryIdAndJudgeId(long entryId, long judgeId);
}
