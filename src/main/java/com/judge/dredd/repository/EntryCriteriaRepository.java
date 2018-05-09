package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.EntryCriteria;

public interface EntryCriteriaRepository extends CrudRepository<EntryCriteria, Long>{

	@Query(value = "select ec.* from entrycriteria ec where ec.entry_id = ?1", nativeQuery = true)
	List<EntryCriteria> findByEntryId(long entryId);
}
