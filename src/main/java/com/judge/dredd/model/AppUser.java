package com.judge.dredd.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(unique=true)
	private String username;

	private String password;

	private int userType;
	
	private boolean isPWReset;

	private boolean withTAC;
	
	private String email;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "judges")
	private List<Entry> entries = new LinkedList<>();

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appUser")
	private List<Comments> comments= new LinkedList<>();
	
	public AppUser() {
	}

	public AppUser(Long id, String username, String password, int userType) {
		super();
		this.userId = id;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public boolean isPWReset() {
		return isPWReset;
	}

	public void setPWReset(boolean isPWReset) {
		this.isPWReset = isPWReset;
	}

	public boolean isWithTAC() {
		return withTAC;
	}

	public void setWithTAC(boolean withTAC) {
		this.withTAC = withTAC;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

	
}
