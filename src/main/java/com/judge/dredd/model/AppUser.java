package com.judge.dredd.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	private String username;
	
	private String password;
	
	private int userType;
	
	@SuppressWarnings("unused")
	private boolean withTAC;

	@JsonIgnore
	@ManyToMany(mappedBy = "judges")
	private List<Entry> entries = new LinkedList<>();
	
	public AppUser() {}
	
	
	
	public AppUser(Long id, String username, String password, int userType) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}


	
}
