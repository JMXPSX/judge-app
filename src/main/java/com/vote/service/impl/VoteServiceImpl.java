package com.vote.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.vote.dto.BoothDTO;
import com.vote.dto.ParticipantDTO;
import com.vote.dto.VoteDTO;
import com.vote.model.Booth;
import com.vote.model.Participant;
import com.vote.model.Vote;
import com.vote.repository.BoothRepository;
import com.vote.repository.ParticipantRepository;
import com.vote.repository.VoteRepository;
import com.vote.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VoteServiceImpl.class);

	@Autowired
	private ParticipantRepository participantRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private BoothRepository boothRepository;
	
	@Inject
	private SimpMessagingTemplate webSocket;
	
	@Override
	public String vote(long eventId, long participantId, String boothIds) {
		// validate event;
		if(9 != eventId){
			return "invalid event id: "+eventId;
		}
		
		// validate participant;
		Participant participant = participantRepository.findById(participantId).orElse(null);
		if(null == participant){
			return "participant: "+participantId+" not found";
		}
		
		//validate booth
		String[] booths = boothIds.split(",");
		
		if(booths.length != 3){
			return "invalid booths :"+boothIds;
		}
		
		List<Long> ids = new ArrayList<>();
		try{
			for(String s : booths){
				ids.add(Long.parseLong(s));
			}
			LOGGER.info("participantId: "+ participantId +" BOOTHS : "+Arrays.toString(ids.toArray()));
		}catch (Exception e){
			return "unparsable boothIds: "+boothIds;
		}
		
		List<Vote> models = new ArrayList();
		Date now = new Date();
		
		for(long boothId : ids){
			Booth boothModel = boothRepository.findById(boothId).orElse(null);
			if(null == boothModel){
				return "Booth: "+boothId+" not found";
			}
			
			//check votes
			List<Vote> votes = voteRepository.findByParticipant_participantId(participant.getParticipantId());
			LOGGER.info("votes size: "+votes.size());
			if(votes.size() != 0){ //put 0 to DB
				return "exceeded vote capacity";
				
			}else if (null != votes.stream().filter(vote -> vote.getBooth().getBoothId() == boothId).findFirst().orElse(null)){
				return "participant "+participantId+" already voted for booth "+boothId;
			}else{
				Vote model = new Vote();
				model.setBooth(boothModel);
				model.setParticipant(participant);
				model.setEventId(eventId);
				model.setDate(now);
				
				if (null == models.stream().filter(m -> m.getBooth().getBoothId() == boothId).findFirst().orElse(null)){
					models.add(model);
				}else{
					return "duplicate booths";
				}
			}
		}
		
		if(models.size() == 3){
			voteRepository.saveAll(models);
		}else{
			return "voting failed";
		}
		
		
		
		
		
		
		
		return "done";
	}

	@Override
	public VoteDTO getResults(long eventId) {

		VoteDTO voteDTO = new VoteDTO();
		
		boolean isExec = false;
		
		try{
			List<Booth> booths = Lists.newArrayList(boothRepository.findAll());
			for(Booth b : booths){
				BoothDTO booth = new BoothDTO();
				booth.setBoothName(b.getBoothName());
				booth.setBoothTT(b.getBoothTt());
				booth.setBoothIg(b.getBoothIg());
				booth.setDescription(b.getBoothDesc());
				booth.setBoothId(b.getBoothId());
				booth.setOrder(b.getOrder());
				voteDTO.add(booth);
			}
			
			List<Vote> votes = voteRepository.findByEventId(eventId);
			
			
			
			for(Vote vote : votes){
				Participant p1 = vote.getParticipant(); 
				
				if("7".equalsIgnoreCase(p1.getLevel())
						||"6".equalsIgnoreCase(p1.getLevel())
						||"5".equalsIgnoreCase(p1.getLevel())
						||"4".equalsIgnoreCase(p1.getLevel())
						||"3".equalsIgnoreCase(p1.getLevel())
						||"2".equalsIgnoreCase(p1.getLevel())
						||"1".equalsIgnoreCase(p1.getLevel())
						||"1".equalsIgnoreCase(p1.getLevel())
						||"ACCENTURE LEADERSHIP".equals(p1.getLevel())
						||"VIP Guest".equalsIgnoreCase(p1.getIg())){
					
					isExec = true;
				
					BoothDTO booth = voteDTO.getTally().stream().filter( b -> b.getBoothId() == vote.getBooth().getBoothId()).findFirst().orElse(null);
					if(null != booth){
						booth.setTotal(booth.getTotal() + 1);
						voteDTO.getTally().remove(booth);
						voteDTO.add(booth);
					}
					
					if(null == voteDTO.getDate() || voteDTO.getDate().before(vote.getDate())){
						ParticipantDTO p = new ParticipantDTO();
						p.setEid(vote.getParticipant().getEid());
						p.setFirstName(vote.getParticipant().getFirstName());
						p.setLastName(vote.getParticipant().getLastName());
						p.setIg(vote.getParticipant().getIg());
						p.setLevel(vote.getParticipant().getLevel());
						p.setParticipantId(vote.getParticipant().getParticipantId());
						voteDTO.setParticipant(p);
						voteDTO.setDate(vote.getDate());
					}
					
				}else{
					isExec = false;
				}
											
			}
		} catch (Exception e){
			e.printStackTrace();
			voteDTO.setMessage(e.getMessage());
		}
		
		if(isExec){
			webSocket.convertAndSend("/vote/result", voteDTO);
		}
		

		return voteDTO;
	}

}
