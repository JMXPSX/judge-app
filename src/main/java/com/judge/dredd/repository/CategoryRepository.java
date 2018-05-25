package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	Category findByName(String categpryName);
	
	@Query(value = "select c.* from category c where c.event_id = ?1", nativeQuery = true)
	List<Category> findCategoriesByEventId(long eventId);
}
