package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.display.dto.DisplayCriteriaDTO;
import com.judge.dredd.display.dto.DisplayEntriesDTO;
import com.judge.dredd.display.dto.DisplayEventDTO;
import com.judge.dredd.display.dto.DisplayScoreDTO;
import com.judge.dredd.display.dto.DisplayScoreSummaryDTO;
import com.judge.dredd.display.dto.DisplayScoreSummaryDTO.Criteria;
import com.judge.dredd.display.dto.DisplayScoreSummaryDTO.Entry;
import com.judge.dredd.display.dto.DisplayScoreSummaryDTO.Judge;
import com.judge.dredd.dto.CriteriaDTO;
import com.judge.dredd.dto.EntryCriteriaDTO;
import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.dto.EventDetailDTO;
import com.judge.dredd.dto.ScoreDTO;
import com.judge.dredd.model.Notes;
import com.judge.dredd.service.CriteriaService;
import com.judge.dredd.service.EntryCriteriaService;
import com.judge.dredd.service.EntryService;
import com.judge.dredd.service.EventDetailService;
import com.judge.dredd.service.EventService;
import com.judge.dredd.service.NotesService;
import com.judge.dredd.service.ScoreService;

@Service
public class EventServiceImpl implements EventService{

	@Autowired
	private EventDetailService eventDetailService;
	
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private EntryCriteriaService entryCriteriaService;
	
	@Autowired
	private CriteriaService criteriaService;
	
	@Autowired
	private NotesService notesService;
	
	@Override
	public DisplayEventDTO getAllForDisplay(long eventId, long judgeId) {
		DisplayEventDTO displayEventDTO = new  DisplayEventDTO();
		
		//1. get the event
		EventDetailDTO event = eventDetailService.getOne(eventId);
		displayEventDTO.setEventName(event.getEventName());
		displayEventDTO.setEventId(event.getId());
		displayEventDTO.setJudgeId(judgeId);
		displayEventDTO.setEntries(new ArrayList<>());
		
		boolean FINAL = true;
		
		//entries within the event
		List<EntryDTO> entries = entryService.getAllEntriesByEventId(eventId);
		for(EntryDTO e :entries){
			DisplayEntriesDTO de = new DisplayEntriesDTO();
			de.setEntryId(e.getEntryId());
			de.setEntryName(e.getEntryName());
			de.setEntryDescription(e.getEntryDescription());
			de.setEntryOwner(e.getOwner());
			de.setCriteria(new ArrayList<>());
			
			Notes n = notesService.findByEntryIdAndJudgeId(e.getEntryId(), judgeId);
			if(null != n){
				de.setNoteId(n.getNoteId());
				de.setNote(n.getNote());
			}
			
			
			boolean DONE = true;
			for(EntryCriteriaDTO ecDTO : entryCriteriaService.getAllEntriesByEntryId(e.getEntryId())){
				
				
				CriteriaDTO cdto = criteriaService.getOne(ecDTO.getCriteriaId());
				
				ScoreDTO score = scoreService.getScoresByEventIdAndEntryIdAndJudgeIdAndCriteriaId(eventId, de.getEntryId(), judgeId, cdto.getCriteriaId());
				
				if(null != score){
					DisplayCriteriaDTO dc = new DisplayCriteriaDTO();
					dc.setCriteriaId(cdto.getCriteriaId());
					dc.setCriteriaName(cdto.getCriteriaName());
					dc.setMaxValue(cdto.getMaxValue());
					dc.setMinValue(cdto.getMinValue());
					
					
					DisplayScoreDTO dsdto = new DisplayScoreDTO();
					dsdto.setJudgeId(score.getJudgeId());
					dsdto.setScore(score.getScore());
					dsdto.setScoreId(score.getScoreId());
					dc.setScore(dsdto);
					
					// will be forever false if atleast one is false
					DONE = DONE && score.isDone();
					de.getCriteria().add(dc);
				}
				
				
			}
			de.setDone(DONE);
			
			FINAL = FINAL && DONE;
			
			if(!de.getCriteria().isEmpty()){
				displayEventDTO.getEntries().add(de);
			}
		}
		displayEventDTO.setFinal(FINAL);
		
		
		return displayEventDTO;
	}
		
	
	public DisplayScoreSummaryDTO getFinalizedScoreSummary(long eventId){
		DisplayScoreSummaryDTO d = new DisplayScoreSummaryDTO(); 
		d.setEventId(eventId);
		
		//all the finalized data are here
		List<ScoreDTO> sdto = scoreService.getFinalizedScoresByEventId(eventId);
		
		//the judge ids
		Set<Long> judgeIds = sdto.stream().map(ScoreDTO::getJudgeId).collect(Collectors.toSet());

		System.out.println("judgeIds: "+judgeIds.size());
		judgeIds.forEach(jid -> {
			
			Judge judge = d.new Judge();
			judge.setJudgeId(jid);
			
			//the entries for each judge
			Set<Long> entryIds = sdto.stream().filter(e -> e.getJudgeId() == jid).map(ScoreDTO::getEntryId).collect(Collectors.toSet());
			entryIds.forEach(eid -> {
				Entry entry = d.new Entry();
				entry.setEntryId(eid);
						
				//the criteria for each entry
						Set<Long> criteriaIds = sdto.stream().filter(c -> c.getJudgeId() == jid && c.getEntryId() == eid).map(ScoreDTO::getCriteriaId).collect(Collectors.toSet());
						criteriaIds.forEach(cid -> {
							Criteria criteria = d.new Criteria();
							
							//the final filtered data
							ScoreDTO x = sdto.stream().filter(z -> z.getJudgeId() == jid && z.getEntryId() == eid && z.getCriteriaId() == cid).findFirst().get();
							criteria.setCriteriaId(x.getCriteriaId());
							criteria.setScore(x.getScore());
							entry.addCriteria(criteria);
						});
						judge.addEntry(entry);		
					});
			d.addJudge(judge);
		});
		
		
	//have a nice day! :)
		
		return d;
	}
	
}
