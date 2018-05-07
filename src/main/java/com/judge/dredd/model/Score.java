package com.judge.dredd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="score")
public class Score {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "score_id")
	private Long scoreId;
	
	//the event
	private int eventDetailId;
	
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
	
//	@OneToOne
//	@JoinColumn(name="entryId")
//	private Entry entry;
	
//	@OneToOne
//	@JoinColumn(name = "eventDetailId")
//	private EventDetail eventDetail;
	
	//@OneToOne
	//@JoinColumn(name = "criteriaId")
	//private Criteria criteria;

	public Long getScoreId() {
		return scoreId;
	}

	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}

	

	public int getEventDetailId() {
		return eventDetailId;
	}

	public void setEventDetailId(int eventDetailId) {
		this.eventDetailId = eventDetailId;
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

//	public EventDetail getEventDetail() {
//		return eventDetail;
//	}
//
//	public void setEventDetail(EventDetail eventDetail) {
//		this.eventDetail = eventDetail;
//	}

	//public Criteria getCriteria() {
	//	return criteria;
	//}

	//public void setCriteria(Criteria criteria) {
	//	this.criteria = criteria;
	//}

//	public Entry getEntry() {
//		return entry;
//	}
//
//	public void setEntry(Entry entry) {
//		this.entry = entry;
//	}

	
	
}
