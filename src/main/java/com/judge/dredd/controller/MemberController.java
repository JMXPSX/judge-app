package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.MemberDTO;
import com.judge.dredd.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@PostMapping("/dredd/api/member/entry/{entryId")
	public ResponseEntity<?> addMember (@RequestParam long entryId, @RequestBody MemberDTO memberDTO){
		return new ResponseEntity<>(memberService.addMember(entryId, memberDTO), HttpStatus.OK);
	}
}
