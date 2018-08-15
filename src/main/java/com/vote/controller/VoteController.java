package com.vote.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vote.model.Chain;
import com.vote.service.VoteService;

@RestController
public class VoteController {
	
	@Autowired
	private VoteService voteService;
	
	@PostMapping("/vote/event/{eventId}/id/{participantId}/entry/boothId={boothIds}")
	public ResponseEntity<?> vote (@RequestParam long eventId, @RequestParam long participantId, @RequestParam String boothIds){
		String msg = voteService.vote(eventId, participantId, boothIds);
		if("done".equalsIgnoreCase(msg)){
			voteService.getResults(eventId);
			// add to chain
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
				
	}
	
	@PostMapping("/vote/event/{eventId}/id/{participantId}/region/{region}/entry/boothId={boothIds}")
	public ResponseEntity<?> vote (@RequestParam long eventId, @RequestParam long participantId, @RequestParam String region, @RequestParam String boothIds){
		String msg = voteService.proxyVote(eventId, participantId, boothIds);
		if("done".equalsIgnoreCase(msg)){
			
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String validityStart = sdf.format(now);
			String validityEnd = sdf.format(now);
			
			Chain chain = new Chain();
			chain.setCouponId(String.valueOf(now.getTime()));
			chain.setCouponName("Election name");
			chain.setCompany(region);
			chain.setClaimer("voter name");
			chain.setCouponType("Blockchain Vote");
			chain.setValidityStart(validityStart);
			chain.setValidityEnd(validityEnd);
			
			voteService.getResults(eventId, chain);
			// add to chain
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
				
	}
	
	@GetMapping("/vote/result/event/{eventId}")
	public ResponseEntity<?> getResults (@RequestParam long eventId){
		return new ResponseEntity<>(voteService.getResults(eventId), HttpStatus.OK);
	}
	
	@GetMapping("/vote/callchain")
	public ResponseEntity<?> callChain (){
		return new ResponseEntity<>(voteService.callChain(null), HttpStatus.OK);
	}
	
	@PostMapping("/vote/callchain")
	public ResponseEntity<?> callChain (@RequestBody Chain chain){
		return new ResponseEntity<>(voteService.callChain(chain), HttpStatus.OK);
	}

}
