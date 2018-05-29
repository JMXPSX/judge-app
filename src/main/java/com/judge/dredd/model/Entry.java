package com.judge.dredd.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="entry")
public class Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long entryId;
	
	private String entryName;
	
	@Lob
	@Column( length = 100000 )
	private String entryDescription;
	
    @OneToMany(mappedBy="entry")
	private List<Member> members = new LinkedList<>();
	
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Event.class)
    @JoinColumn(name = "event_id")
	private Event event;
	
	@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = { 
            CascadeType.DETACH, 
            CascadeType.MERGE
        })
        @JoinTable(name = "entry_judge",
            joinColumns = @JoinColumn(name = "entry_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
        )
	private List<AppUser> judges;
		
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
		
	public Entry() {}
	
	public Entry(Long entryId, String entryName, String entryDescription, Event event,
			Category category) {
		super();
		this.entryId = entryId;
		this.entryName = entryName;
		this.entryDescription = entryDescription;
		this.event = event;
		this.category = category;
	}

	public Long getEntryId() {
		return entryId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public String getEntryDescription() {
		return entryDescription;
	}

	public void setEntryDescription(String entryDescription) {
		this.entryDescription = entryDescription;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<AppUser> getJudges() {
		return judges;
	}

	public void setJudges(List<AppUser> judges) {
		this.judges = judges;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
