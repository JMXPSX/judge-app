package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
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
	public CategoryDTO addCategory(CategoryDTO categoryDTO) throws Exception {
		Event e = eventRepository.findById(categoryDTO.getEventId()).orElseThrow(() ->new Exception("Event id "+categoryDTO.getEventId()+" not found"));
		
		Category c =  dtoService.convertToModel(categoryDTO);
		c.setEvent(e);
		c = categoryRepository.save(c);
		return dtoService.convertToDTO(c);
	}

	@Override
	public List<CategoryDTO> getCategoriesByUser(long appUserId) {
		List<CategoryDTO> response = new ArrayList<>();
		List<Category> categories = categoryRepository.findCategoriesByUserId(appUserId);
		categories.forEach(category -> response.add(dtoService.convertToDTO(category)));
		return response;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<CategoryDTO> response = new ArrayList<>();
		List<Category> categories = Lists.newArrayList(categoryRepository.findAll());
		categories.forEach(category -> response.add(dtoService.convertToDTO(category)));
		return response;
	}

}
