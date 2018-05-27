package com.judge.dredd.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="score")
public class Score {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "score_id")
	private Long scoreId;
		
	@ManyToOne(cascade = CascadeType.DETACH, targetEntity = Criteria.class)
    @JoinColumn(name = "criteria_id")
	private Criteria criteria;
	
	private double score;
	
	// done scoring
	private boolean isDone;
	
	@ManyToOne(cascade = CascadeType.DETACH, targetEntity = Tabulator.class)
    @JoinColumn(name = "tabulator_id")
	private Tabulator tabulator;
	
    @JsonIgnore
    @Column(name = "created_date")
    @CreationTimestamp
    private Timestamp createdDate;

    @JsonIgnore
    @Column(name = "updated_date")
    @UpdateTimestamp
    private Timestamp updatedDate;

	public Long getScoreId() {
		return scoreId;
	}

	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Tabulator getTabulator() {
		return tabulator;
	}

	public void setTabulator(Tabulator tabulator) {
		this.tabulator = tabulator;
	}
	
    
    
}
