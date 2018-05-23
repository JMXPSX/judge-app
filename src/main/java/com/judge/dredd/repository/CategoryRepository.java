package com.judge.dredd.repository;

import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	Category findByName(String categpryName);
}
