package com.judge.dredd.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Long id;
	
	private String eventName;
	
	private Date startDate;
	
	private Date endDate;

    @OneToMany(mappedBy="event")
	private List<Entry> entries;
	
    @OneToMany(mappedBy="event")
	private List<Criteria> criteria;
    
    @OneToMany(mappedBy="event")
	private List<Category> category;
    
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = AppUser.class)
    @JoinColumn(name = "app_user_id")
	private AppUser appUser;
    
	public Event() {
		// TODO Auto-generated constructor stub
	}
	
	public Event(Long id, String eventName, Date startDate, Date endDate, AppUser appUser) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.appUser = appUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long eventId) {
		this.id = eventId;
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

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public List<Criteria> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<Criteria> criteria) {
		this.criteria = criteria;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	
	
	
}
