package com.judge.dredd.dto;

public class SytemInfoDTO {

	private String name;
	private String version;
	private String timestamp;
	
	public SytemInfoDTO(String name, String version, String timestamp) {
		super();
		this.name = name;
		this.version = version;
		this.timestamp = timestamp;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


}
