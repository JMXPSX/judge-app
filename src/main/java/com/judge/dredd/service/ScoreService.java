package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.ScoreDTO;

public interface ScoreService {

	ScoreDTO getOne(long id);
	ScoreDTO save(ScoreDTO scoreDTO);
	void delete(long id);
	List<ScoreDTO> getAll();
	void finalizeScore();
	void setScore();
	void updateScore();
}
