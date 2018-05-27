package com.judge.dredd.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private Long id;
	
	private String name;
	
	private String description;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Event.class)
    @JoinColumn(name = "event_id")
	private Event event;
	
    @OneToMany(mappedBy="event")
	private List<Entry> entries;
	
    public Category() {}
    
	public Category(Long id, String name, String description, Event event) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.event = event;
	}

	public Long getId() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
	
}
