package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	Category findByName(String categpryName);
	
	@Query(value = "select c.* from category c where c.event_id = ?1", nativeQuery = true)
	List<Category> findCategoriesByEventId(long eventId);
	
	public List<Category> findByEntries_judges_userId(long appUserId);
	
	@Query(value = "select c.* from category c, tabulator t, entry e where c.event_id = t.event_id and t.entry_id = e.entry_id and c.category_id = e.category_id and t.user_id = ?1 group by category_id", nativeQuery=true)
	public List<Category> findCategoriesByUserId(long appUserId);
}
