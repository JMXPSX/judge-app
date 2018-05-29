package com.judge.dredd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.MemberDTO;
import com.judge.dredd.model.Entry;
import com.judge.dredd.model.Member;
import com.judge.dredd.repository.EntryRepository;
import com.judge.dredd.repository.MemberRepository;
import com.judge.dredd.service.DtoService;
import com.judge.dredd.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository; 

	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private DtoService dtoService;
	
	@Override
	public MemberDTO addMember(long entryId, MemberDTO memberDTO) {
		Member m = dtoService.convertToModel(memberDTO);
		Entry e = entryRepository.findById(entryId).get();
		m.setEntry(e);
		m = memberRepository.save(m);
		return dtoService.convertToDTO(m);
	}

}
