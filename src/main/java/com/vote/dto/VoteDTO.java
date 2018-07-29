package com.vote.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VoteDTO {

	private String message;
	
	private List<BoothDTO> tally = new ArrayList<>();
	
	private ParticipantDTO participant;
	
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date date;
	
	private String image;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<BoothDTO> getTally() {
		return tally;
	}

	public void setTally(List<BoothDTO> tally) {
		this.tally = tally;
	}

	public ParticipantDTO getParticipant() {
		return participant;
	}

	public void setParticipant(ParticipantDTO participant) {
		this.participant = participant;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void add (BoothDTO booth){
		tally.add(booth);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
 