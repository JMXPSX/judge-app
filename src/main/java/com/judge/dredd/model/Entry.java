package com.judge.dredd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="entry")
public class Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private String entryName;
	
	private String entryDescription;
	
	private String userName;
	
	private int displayType;
	
	private String entry;
}
