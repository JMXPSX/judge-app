package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.dto.EntryJudgeDTO;

public interface EntryService {
	EntryDTO getOne(long id);
	EntryDTO save(EntryDTO entryDTO) throws Exception;
	void delete(long id);
	public List<EntryDTO> getAll();
	
	public List<EntryDTO> getAllEntriesByEventId(long eventId);
	
	public List<EntryDTO> getEntriesByEventIdAndCategoryIdAndUserId(long eventId, long categoryId, long userId);
	
	public List<EntryDTO> getEntriesByEventIdAndUserId(long eventId, long userId);
	
	public EntryDTO addEntryWithMembers(EntryDTO entryDTO) throws Exception;
	
	String assignJudges(long entryId, List<Long> judges);
	
	public String finalizeEntries(long eventId, long judgeId, String submitterName);
	
	String removeJudges(long entryId, List<Long> judges);
	
	public String assignUserToEntry(long entryId, long appUserId);
	
	public String assignJudges(List<EntryJudgeDTO> params);
	
//	String removeJudge(long entryId, long judgeId);
}
