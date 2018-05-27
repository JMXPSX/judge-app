package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.dto.MemberDTO;
import com.judge.dredd.model.AppUser;
import com.judge.dredd.model.Entry;
import com.judge.dredd.repository.AppUserRepository;
import com.judge.dredd.repository.EntryRepository;
import com.judge.dredd.repository.MemberRepository;
import com.judge.dredd.service.DtoService;
import com.judge.dredd.service.EntryService;

@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	private EntryRepository entryRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private DtoService dtoService;

	@Autowired
	private AppUserRepository userRepository;

	@Override
	public EntryDTO getOne(long id) {

		Entry obj = entryRepository.findById(id).get();

		return dtoService.convertToDTO(obj);
	}

	@Override
	public EntryDTO save(EntryDTO entryDTO) {

		Entry obj = dtoService.convertToModel(entryDTO);
		obj = entryRepository.save(obj);

		List<MemberDTO> members = entryDTO.getMembers();
		members.forEach(member -> memberRepository.save(dtoService.convertToModel(member)));

		return dtoService.convertToDTO(obj);
	}

	@Override
	public void delete(long id) {

		entryRepository.deleteById(id);

	}

	@Override
	public List<EntryDTO> getAll() {

		List<Entry> obj = Lists.newArrayList(entryRepository.findAll());
		List<EntryDTO> objDTo = new ArrayList<>();

		obj.forEach(i -> {
			objDTo.add(dtoService.convertToDTO(i));
		});

		return objDTo;
	}

	@Override
	public List<EntryDTO> getAllEntriesByEventId(long eventId) {
		List<Entry> obj = Lists.newArrayList(entryRepository.findAllByEventId(eventId));
		List<EntryDTO> objDTo = new ArrayList<>();

		obj.forEach(i -> {
			objDTo.add(dtoService.convertToDTO(i));
		});

		return objDTo;
	}

	@Override
	public List<EntryDTO> getEntriesByEventIdAndCategoryIdAndUserId(long eventId, long categoryId, long appUserId) {
		List<EntryDTO> dtos = new ArrayList<EntryDTO>();
		List<Entry> entries = entryRepository.findByEventIdAndCategory_idAndJudges_userId(eventId, categoryId,
				appUserId);
		entries.forEach(entry -> dtos.add(dtoService.convertToDTO(entry)));
		return dtos;
	}

	@Override
	public EntryDTO addEntryWithMembers(EntryDTO entryDTO) {
		Entry obj = dtoService.convertToModel(entryDTO);
		obj = entryRepository.save(obj);

		List<MemberDTO> members = entryDTO.getMembers();
		members.forEach(member -> memberRepository.save(dtoService.convertToModel(member)));

		return dtoService.convertToDTO(obj);
	}

	@Override
	public String assignJudges(long entryId, List<Long> judgesId) {

		String value = "User is already a judge or does not exist.";
		int count = 0;
		Entry entry = entryRepository.findById(entryId).get();
		List<AppUser> judges = new ArrayList<>();
	
		for (Long id : judgesId) {
			boolean flag = false;
			for (AppUser appUser : entry.getJudges()) {
				if (appUser.getUserId() == id) {
					flag = true;
				}
			}
			
			if(!flag) {
				judges.add(userRepository.findById(id).get());
			}
		}
		count = entry.getJudges().size();
		
		judges.addAll(entry.getJudges());
		 
		entry.setJudges(judges);

		entryRepository.save(entry);
		
		if (judges.size() > count) {
			value = "Judge successfully assigned";
		}

		return value;
	}

	@Override
	public String removeJudges(long entryId, List<Long> judges) {
		// TODO Auto-generated method stub
		return "Not Implemented";
	}

}
