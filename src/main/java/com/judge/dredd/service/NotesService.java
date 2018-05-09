package com.judge.dredd.service;

import com.judge.dredd.dto.NotesDTO;
import com.judge.dredd.model.Notes;

public interface NotesService {

	public Notes findByEntryIdAndJudgeId(long entryId, long judgeId);
	
	public String updateNotes(NotesDTO notesDTO);
}
