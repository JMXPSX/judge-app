package com.judge.dredd.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="criteria")
public class Criteria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long criteriaId;
	
	private String criteriaName;
	
	private String criteriaDescription;
	
	private int minValue;
	
	private int maxValue;

	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Event.class)
    @JoinColumn(name = "event_id")
	private Event event;

	public Criteria() {
		// TODO Auto-generated constructor stub
	}
	
	public Criteria(Long criteriaId, String criteriaName, String criteriaDescription, int minValue, int maxValue,
			Event event) {
		super();
		this.criteriaId = criteriaId;
		this.criteriaName = criteriaName;
		this.criteriaDescription = criteriaDescription;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.event = event;
	}



	public Long getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(Long criteriaId) {
		this.criteriaId = criteriaId;
	}

	public String getCriteriaName() {
		return criteriaName;
	}

	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}

	public String getCriteriaDescription() {
		return criteriaDescription;
	}

	public void setCriteriaDescription(String criteriaDescription) {
		this.criteriaDescription = criteriaDescription;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public Event getEvents() {
		return event;
	}

	public void setEvents(Event event) {
		this.event = event;
	}

	
	
}
