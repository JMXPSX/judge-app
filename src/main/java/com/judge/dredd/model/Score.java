package com.judge.dredd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="score")
public class Score {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	//the event
	private int eventId;
	
	// the entry
	private int entryId;
	
	//the judge
	private int judgeId;
	
	private int criteriaId;
	
	private int score;
	
	// done scoring
	private boolean isDone;
	
	// judge finalized
	private boolean isFinal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public int getJudgeId() {
		return judgeId;
	}

	public void setJudgeId(int judgeId) {
		this.judgeId = judgeId;
	}

	public int getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(int criteriaId) {
		this.criteriaId = criteriaId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	
	
}
