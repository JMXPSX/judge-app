package com.judge.dredd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="entrycriteria")
public class EntryCriteria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "entry_criteria_id")
	private Long entryCriteriaId;
	
	private long entryId;
	private long criteriaId;
	public Long getEntryCriteriaId() {
		return entryCriteriaId;
	}
	public void setEntryCriteriaId(Long entryCriteriaId) {
		this.entryCriteriaId = entryCriteriaId;
	}
	public long getEntryId() {
		return entryId;
	}
	public void setEntryId(long entryId) {
		this.entryId = entryId;
	}
	public long getCriteriaId() {
		return criteriaId;
	}
	public void setCriteriaId(long criteriaId) {
		this.criteriaId = criteriaId;
	}
	
	
}
