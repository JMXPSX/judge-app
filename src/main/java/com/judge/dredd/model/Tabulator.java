package com.judge.dredd.model;

import java.sql.Timestamp;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tabulator")
public class Tabulator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tabulator_id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.DETACH, targetEntity = Event.class)
    @JoinColumn(name = "event_id")
	private Event event;
	
	@ManyToOne(cascade = CascadeType.DETACH, targetEntity = Entry.class)
    @JoinColumn(name = "entry_id")
	private Entry entry;
	
	@ManyToOne(cascade = CascadeType.DETACH, targetEntity = AppUser.class)
    @JoinColumn(name = "app_user_id")
	private AppUser judge;
	
    @OneToMany(mappedBy="tabulator")
	private List<Score> scores;
	
	private boolean isFinal;
	
    @JsonIgnore
    @Column(name = "created_date")
    @CreationTimestamp
    private Timestamp createdDate;

    @JsonIgnore
    @Column(name = "updated_date")
    @UpdateTimestamp
    private Timestamp updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public AppUser getJudge() {
		return judge;
	}

	public void setJudge(AppUser judge) {
		this.judge = judge;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
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

	
}
