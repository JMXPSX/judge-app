package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.CategoryDTO;

public interface CategoryService {

	public List<CategoryDTO> getCategoriesByEvent (long eventId);
	
	public CategoryDTO addCategory(CategoryDTO categoryDTO);
	
	public List<CategoryDTO> getCategoriesByUser (long appUserId);
}
