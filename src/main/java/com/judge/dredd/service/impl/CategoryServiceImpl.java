package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.CategoryDTO;
import com.judge.dredd.model.Category;
import com.judge.dredd.model.Event;
import com.judge.dredd.repository.CategoryRepository;
import com.judge.dredd.repository.EventRepository;
import com.judge.dredd.service.CategoryService;
import com.judge.dredd.service.DtoService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private DtoService dtoService;
	
	@Autowired
	private EventRepository eventRepository;

	@Override
	public List<CategoryDTO> getCategoriesByEvent(long eventId) {
		List<CategoryDTO> response = new ArrayList<>();
		List<Category> categories = categoryRepository.findCategoriesByEventId(eventId);
		categories.forEach(category -> response.add(dtoService.convertToDTO(category)));
		return response;
	}

	@Override
	public CategoryDTO addCategory(CategoryDTO categoryDTO) {
		Category c =  dtoService.convertToModel(categoryDTO);
		
		Event e = eventRepository.findById(categoryDTO.getEventId()).get();
		c.setEvent(e);
		c = categoryRepository.save(c);
		return dtoService.convertToDTO(c);
	}

	@Override
	public List<CategoryDTO> getCategoriesByUser(long appUserId) {
		List<CategoryDTO> response = new ArrayList<>();
		List<Category> categories = categoryRepository.findByEntries_judges_userId(appUserId);
		categories.forEach(category -> response.add(dtoService.convertToDTO(category)));
		return response;
	}

}
