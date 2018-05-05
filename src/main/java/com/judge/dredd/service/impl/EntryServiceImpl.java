package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.model.Entry;
import com.judge.dredd.repository.EntryRepository;
import com.judge.dredd.service.DtoService;
import com.judge.dredd.service.EntryService;

@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	private EntryRepository entryRepository;

	@Autowired
	private DtoService dtoService;

	@Override
	public EntryDTO getOne(long id) {

		Entry obj = entryRepository.findById(id).get();

		return dtoService.convertToDTO(obj);
	}

	@Override
	public EntryDTO save(EntryDTO entryDTO) {
		
		Entry obj = dtoService.convertToModel(entryDTO);
		obj = entryRepository.save(obj);

		return dtoService.convertToDTO(obj);
	}

	@Override
	public void delete(long id) {
		
		entryRepository.deleteById(id);

	}

	@Override
	public List<EntryDTO> getAll() {
		
		List<Entry> obj = Lists.newArrayList(entryRepository.findAll());
		List<EntryDTO> objDTo = new ArrayList<>();

		obj.forEach(i -> {
			objDTo.add(dtoService.convertToDTO(i));
		});

		return objDTo;
	}

}
