package com.judge.dredd.display.dto;

import java.util.ArrayList;
import java.util.List;

public class DisplayScoreSummaryDTO {

	private long eventId;
	private List<Judge> judges = new ArrayList();
	
	
	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public List<Judge> getJudges() {
		return judges;
	}

	public void addJudge(Judge judge){
		judges.add(judge);
	}

	public class Judge{
		long judgeId;
		List<Entry> entries = new ArrayList();

		public List<Entry> getEntries() {
			return entries;
		}

		public void addEntry(Entry entry){
			entries.add(entry);
		}

		public long getJudgeId() {
			return judgeId;
		}

		public void setJudgeId(long judgeId) {
			this.judgeId = judgeId;
		}
		
	}
	
	public class Entry{
		long entryId;
		List<Criteria> criteria = new ArrayList();

		public List<Criteria> getCriteria() {
			return criteria;
		}

		public void addCriteria(Criteria criteria) {
			this.criteria.add(criteria);
		}

		public long getEntryId() {
			return entryId;
		}

		public void setEntryId(long entryId) {
			this.entryId = entryId;
		}
		
		
	}
	
	public class Criteria{
		long criteriaId;
		String criteriaName;
		long score;
		public long getCriteriaId() {
			return criteriaId;
		}
		public void setCriteriaId(long criteriaId) {
			this.criteriaId = criteriaId;
		}
		public String getCriteriaName() {
			return criteriaName;
		}
		public void setCriteriaName(String criteriaName) {
			this.criteriaName = criteriaName;
		}
		public long getScore() {
			return score;
		}
		public void setScore(long score) {
			this.score = score;
		}
		
		
	}
}
