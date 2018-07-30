package com.vote.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

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

	@Autowired
	private ParticipantRepository participantRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private BoothRepository boothRepository;
	
	@Inject
	private SimpMessagingTemplate webSocket;
	
	@Override
	public String vote(long eventId, long participantId, long boothId) {
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
		Booth boothModel = boothRepository.findById(boothId).orElse(null);
		if(null == boothModel){
			return "Booth: "+boothId+" not found";
		}
		
		//check votes
		List<Vote> votes = voteRepository.findByParticipant_participantId(participant.getParticipantId());
		if(votes.size() >= 3){
			return "exceeded vote capacity";
			
		}else if (null != votes.stream().filter(vote -> vote.getBooth().getBoothId() == boothId).findFirst().orElse(null)){
			return "participant "+participantId+" already voted for booth "+boothId;
		}else{
			Vote model = new Vote();
			model.setBooth(boothModel);
			model.setParticipant(participant);
			model.setEventId(eventId);
			model.setDate(new Date());
			voteRepository.save(model);
		}
		
		
		return "done";
	}

	@Override
	public VoteDTO getResults(long eventId) {

		VoteDTO voteDTO = new VoteDTO();
		
		
		try{
			List<Booth> booths = Lists.newArrayList(boothRepository.findAll());
			for(Booth b : booths){
				BoothDTO booth = new BoothDTO();
				booth.setBoothName(b.getBoothName());
				booth.setBoothTT(b.getBoothTt());
				booth.setBoothIg(b.getBoothIg());
				booth.setDescription(b.getBoothDesc());
				booth.setBoothId(b.getBoothId());
				voteDTO.add(booth);
			}
			
			List<Vote> votes = voteRepository.findByEventId(eventId);
			
			for(Vote vote : votes){
				
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
							
			}
		} catch (Exception e){
			e.printStackTrace();
			voteDTO.setMessage(e.getMessage());
		}
		
		webSocket.convertAndSend("/vote/result", voteDTO);

		return voteDTO;
	}

}
