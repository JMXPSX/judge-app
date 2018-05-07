package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Score;

public interface ScoreRepository extends CrudRepository<Score, Long>{

	@Query(value = "select s.* from score s where s.eventId = ?1 and s.judgeId = ?2", nativeQuery = true)
	public List<Score> findScoreByEventIdAndJudgeId(int eventId, int judgeId);
	
	@Query(value = "select s.* from score s where s.eventId = ?1 and s.isFinal = true", nativeQuery = true)
	public List<Score> findFinalScoreByEventId(int eventId);
}
