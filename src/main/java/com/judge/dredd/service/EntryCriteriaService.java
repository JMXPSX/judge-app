package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.EntryCriteriaDTO;

public interface EntryCriteriaService {

	public List<EntryCriteriaDTO> getAllEntriesByEntryId(long entryId);
}
