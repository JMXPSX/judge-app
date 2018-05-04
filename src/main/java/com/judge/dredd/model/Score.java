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
}
