package com.judge.dredd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.NotesDTO;
import com.judge.dredd.model.Notes;
import com.judge.dredd.repository.NotesRepository;
import com.judge.dredd.service.NotesService;

@Service
public class NotesServiceImpl implements NotesService {
	
	@Autowired
	NotesRepository notesRepository;

	@Override
	public Notes findByEntryIdAndJudgeId(long entryId, long judgeId) {
		Notes n = notesRepository.findByEntryIdAndJudgeId(entryId, judgeId);
		return n;
	}

	@Override
	public String updateNotes(NotesDTO notesDTO) {
		Notes n = notesRepository.findById(notesDTO.getNoteId()).get();
		
		if(n != null){
			n.setNote(notesDTO.getNote());
			notesRepository.save(n);
		}
		
		return "message: Done";
	}

}
