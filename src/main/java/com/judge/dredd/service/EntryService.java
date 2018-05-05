package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.EntryDTO;

public interface EntryService {
	EntryDTO getOne(long id);
	EntryDTO save(EntryDTO entryDTO);
	void delete(long id);
	List<EntryDTO> getAll();
}
