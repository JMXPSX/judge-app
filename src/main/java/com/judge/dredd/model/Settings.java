package com.judge.dredd.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Settings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "settings_id")
	private long settingsId;
	
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Event.class)
    @JoinColumn(name = "event_id")
	private Event event;
	
	@Column(name = "settings_key")
	private String key;
	
	private String dataType;
	
	private String value;
	
	public Settings() {}
	
	public Settings(long settingsId, Event event, String key, String dataType, String value) {
		super();
		this.settingsId = settingsId;
		this.event = event;
		this.key = key;
		this.dataType = dataType;
		this.value = value;
	}

	public long getSettingsId() {
		return settingsId;
	}

	public void setSettingsId(long settingsId) {
		this.settingsId = settingsId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
