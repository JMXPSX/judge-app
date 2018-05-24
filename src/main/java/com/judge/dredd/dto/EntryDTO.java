package com.judge.dredd.dto;

import java.util.List;

public class EntryDTO {

	private Long entryId;
	private Long eventId;
	private long categoryId;
	
	private String entryName;
	private String entryDescription;
	private String owner;
	
	List<MemberDTO> members;	
	

	
}
