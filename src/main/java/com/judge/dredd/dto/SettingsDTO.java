package com.judge.dredd.dto;

public class SettingsDTO extends BaseDTO{

	private long settingsId;
	private String key;
	private String value;
	private long eventId;
	private String dataType;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public long getSettingsId() {
		return settingsId;
	}
	public void setSettingsId(long settingsId) {
		this.settingsId = settingsId;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}
