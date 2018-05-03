package com.judge.dredd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="criteria")
public class Criteria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private String criteriaName;
	
	private String criteriaDescription;
	
	private int minValue;
	
	private int maxValue;
}
