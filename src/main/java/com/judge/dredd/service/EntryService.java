package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.EntryDTO;

public interface EntryService {
	EntryDTO getOne(long id);
	EntryDTO save(EntryDTO entryDTO);
	void delete(long id);
	List<EntryDTO> getAll();
	
	public List<EntryDTO> getAllEntriesByEventId(long eventId);
	
	public List<EntryDTO> getEntriesByEventIdAndCategoryIdAndUserId(long eventId, long categoryId, long userId);
	
	public EntryDTO addEntryWithMembers(EntryDTO entryDTO);
}
