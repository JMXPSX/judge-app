package com.vote.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.judge.dredd.dto.SettingsDTO;
import com.judge.dredd.service.SettingsService;
import com.vote.dto.BoothDTO;
import com.vote.dto.ParticipantDTO;
import com.vote.dto.VoteChainDTO;
import com.vote.dto.VoteDTO;
import com.vote.model.Booth;
import com.vote.model.Chain;
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
	
	@Inject
	private SettingsService settingsService;
	
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
		
		List<SettingsDTO> settings = settingsService.getSettingsByEvent(eventId);
		SettingsDTO voteCount = settings.stream().filter(s -> "VOTE_COUNT".equalsIgnoreCase(s.getKey())).findFirst().orElse(null);
		int cnt = 3;
		
		try{
			cnt = Integer.parseInt(voteCount.getValue());
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		if(booths.length != cnt){
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
		
		if(models.size() == cnt){
			voteRepository.saveAll(models);
		}else{
			return "voting failed";
		}
		
		return "done";
	}
	
	@Override
	public VoteDTO getResults(long eventId, Chain chain) {
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
				booth.setOrder(b.getOrder());
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
		
		VoteChainDTO vc = new VoteChainDTO();
		vc.setChain(chain);
		vc.setVoteDTO(voteDTO);
		webSocket.convertAndSend("/vote/result", vc);
		return voteDTO;
	}
	
	@Override
	public VoteDTO getResults(long eventId) {

		VoteDTO voteDTO = new VoteDTO();
		
//		boolean isExec = false;
		
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
//				Participant p1 = vote.getParticipant(); 
				
//				if("7".equalsIgnoreCase(p1.getLevel())
//						||"6".equalsIgnoreCase(p1.getLevel())
//						||"5".equalsIgnoreCase(p1.getLevel())
//						||"4".equalsIgnoreCase(p1.getLevel())
//						||"3".equalsIgnoreCase(p1.getLevel())
//						||"2".equalsIgnoreCase(p1.getLevel())
//						||"1".equalsIgnoreCase(p1.getLevel())
//						||"1".equalsIgnoreCase(p1.getLevel())
//						||"ACCENTURE LEADERSHIP".equals(p1.getLevel())
//						||"VIP Guest".equalsIgnoreCase(p1.getIg())){
//					
//					isExec = true;
				
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
					
//				}else{
//					isExec = false;
//				}
											
			}
		} catch (Exception e){
			e.printStackTrace();
			voteDTO.setMessage(e.getMessage());
		}
		
//		if(isExec){
		Chain chain = callChain(null);
		VoteChainDTO vc = new VoteChainDTO();
		vc.setChain(chain);
		vc.setVoteDTO(voteDTO);
			webSocket.convertAndSend("/vote/result", vc);
//		}
		

		return voteDTO;
	}

	@Override
	public Chain callChain(Chain chain) {
		StringBuffer sb = new StringBuffer();
		try {
			String uri ="http://34.212.98.247/api/coupons/claim";
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String validityStart = sdf.format(now);
			String validityEnd = sdf.format(now);
			
			if(null == chain){
				chain = new Chain();
				chain.setCouponId(String.valueOf(now.getTime()));
				chain.setCouponName("Election name");
				chain.setCompany("vote inc.");
				chain.setClaimer("voter name");
				chain.setCouponType("Blockchain Vote");
				chain.setValidityStart(validityStart);
				chain.setValidityEnd(validityEnd);
			}
			
			
			String s = new ObjectMapper().writeValueAsString(chain);
			LOGGER.info("s::: "+s);
				
			OutputStream os = conn.getOutputStream();
			os.write(s.getBytes());
			os.flush();

//			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//				throw new RuntimeException("Failed : HTTP error code : "
//					+ conn.getResponseCode());
//			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			LOGGER.info("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				LOGGER.info(output);
				sb.append(output);
			}
			
			ObjectMapper m = new ObjectMapper();
			chain = m.readValue(sb.toString(), Chain.class);

			conn.disconnect();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chain;
	}
	
//	public static void main(String[] args) {
//		VoteServiceImpl v = new VoteServiceImpl();
//		v.callChain(null);
//	}

}
