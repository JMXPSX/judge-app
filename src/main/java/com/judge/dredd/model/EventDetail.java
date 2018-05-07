package com.judge.dredd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="eventdetail")
public class EventDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_detail_id")
	private Long eventDetalId;
	
	private String eventName;
	
	private Date startDate;
	
	private Date endDate;

	
	public Long getEventDetalId() {
		return eventDetalId;
	}

	public void setEventDetalId(Long eventDetalId) {
		this.eventDetalId = eventDetalId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	
	
}
