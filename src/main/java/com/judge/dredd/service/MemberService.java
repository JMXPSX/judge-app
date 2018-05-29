package com.judge.dredd.service;

import com.judge.dredd.dto.MemberDTO;

public interface MemberService {

	public MemberDTO addMember(long entryId, MemberDTO memberDTO);
}
