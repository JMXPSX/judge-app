package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Comments;

public interface CommentsRepository extends CrudRepository<Comments, Long>{

//	@Query(value = "select n.* from comments n where n.entry_id = ?1 and n.judge_id = ?2", nativeQuery = true)
	public List<Comments> findByEntry_entryId(long entryId);
	
	public List<Comments> findByEntry_entryIdAndAppUser_userIdAndComment(long entryId, long userId, String comment);
}
