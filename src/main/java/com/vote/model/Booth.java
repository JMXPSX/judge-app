package com.vote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="booth")
public class Booth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="booth_id")
	private Long boothId;
	
	private String boothName;
	
	private String boothIg;
	
	private String boothTt;
	
	private String boothDesc;

	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	public String getBoothName() {
		return boothName;
	}

	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}

	public String getBoothIg() {
		return boothIg;
	}

	public void setBoothIg(String boothIg) {
		this.boothIg = boothIg;
	}

	public String getBoothTt() {
		return boothTt;
	}

	public void setBoothTt(String boothTt) {
		this.boothTt = boothTt;
	}

	public String getBoothDesc() {
		return boothDesc;
	}

	public void setBoothDesc(String boothDesc) {
		this.boothDesc = boothDesc;
	}

		
}
