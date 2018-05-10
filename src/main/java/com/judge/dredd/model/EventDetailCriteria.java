package com.judge.dredd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="eventdetailcriteria")
public class EventDetailCriteria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_detail_criteria_id")
	private Long eventDetailCriteriaId;
	
	private long eventDetailId;
	
	private long criteriaId;

	public Long getEventDetailCriteriaId() {
		return eventDetailCriteriaId;
	}

	public void setEventDetailCriteriaId(Long eventDetailCriteriaId) {
		this.eventDetailCriteriaId = eventDetailCriteriaId;
	}
	
	public long getEventDetailId() {
		return eventDetailId;
	}

	public void setEventDetailId(long eventDetailId) {
		this.eventDetailId = eventDetailId;
	}

	public long getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(long criteriaId) {
		this.criteriaId = criteriaId;
	}
	
	
}
