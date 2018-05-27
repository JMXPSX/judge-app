package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Score;

public interface ScoreRepository extends CrudRepository<Score, Long>{

	@Query(value = "select s.* from score s where s.event_detail_id = ?1 and s.judge_id = ?2", nativeQuery = true)
	public List<Score> findScoreByEventIdAndJudgeId(long eventId, long judgeId);
	
	@Query(value = "select s.* from score s where s.event_detail_id = ?1 and s.is_final = true", nativeQuery = true)
	public List<Score> findFinalScoreByEventId(long eventId);
	
	@Query(value = "select s.* from score s where s.event_detail_id = ?1 and s.entry_id = ?2 and s.judge_id = ?3", nativeQuery = true)
	public List<Score> findScoreByEventIdAndEntryIdAndJudgeId(long eventId, long entryId, long judgeId);
	
	@Query(value = "select s.* from score s where s.event_detail_id = ?1 and s.entry_id = ?2 and s.judge_id = ?3 and s.criteria_id = ?4", nativeQuery = true)
	public Score findScoreByEventIdAndEntryIdAndJudgeIdAndCriteriaId(long eventId, long entryId, long judgeId, long criteriaId);
	
	@Query(value = "select s.* from score s where s.entry_id = ?1 and s.judge_id = ?2", nativeQuery = true)
	public List<Score> findScoreByEntrytIdAndJudgeId(long eventId, long judgeId);
	
	public List<Score> findByCriteria_Event_idAndTabulator_Entry_entryId(long eventId, long entryId);
	
	public List<Score> findByCriteria_Event_idAndTabulator_Entry_entryIdAndTabulator_Judge_userId(long eventId, long entryId, long userId);
}
