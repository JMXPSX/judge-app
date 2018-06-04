package com.judge.dredd.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.dto.MemberDTO;
import com.judge.dredd.model.AppUser;
import com.judge.dredd.model.Criteria;
import com.judge.dredd.model.Entry;
import com.judge.dredd.model.Member;
import com.judge.dredd.model.Score;
import com.judge.dredd.model.Tabulator;
import com.judge.dredd.repository.AppUserRepository;
import com.judge.dredd.repository.CategoryRepository;
import com.judge.dredd.repository.CriteriaRepository;
import com.judge.dredd.repository.EntryRepository;
import com.judge.dredd.repository.MemberRepository;
import com.judge.dredd.repository.ScoreRepository;
import com.judge.dredd.repository.TabulatorRepository;
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
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Autowired
	private CriteriaRepository criteriaRepository;
	
	@Autowired
	private TabulatorRepository tabulatorRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

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
		List<Entry> entries = entryRepository.findByEventIdAndCategory_categoryIdAndJudges_userId(eventId, categoryId,
				appUserId);
		entries.forEach(entry -> {
			System.out.println("member isze: "+ entry.getMembers().size());
			
			EntryDTO e = dtoService.convertToDTO(entry);
			e.setMembers(new ArrayList<>());
			
			List<Member> members = entry.getMembers();
			members.forEach(m -> e.getMembers().add(dtoService.convertToDTO(m)));
			
			
			dtos.add(e);
			});
		return dtos;
	}

	@Override
	public EntryDTO addEntryWithMembers(EntryDTO entryDTO) {
		Entry obj = dtoService.convertToModel(entryDTO);
		final Entry objf = entryRepository.save(obj);

		EntryDTO dto = dtoService.convertToDTO(objf);
		
		if(null == dto.getMembers()){
			dto.setMembers(new ArrayList<>());
		}
		List<MemberDTO> members = entryDTO.getMembers();
		members.forEach(member ->{ 
			Member m = dtoService.convertToModel(member);
			m.setEntry(objf);
			m = memberRepository.save(m);
			
			dto.getMembers().add(dtoService.convertToDTO(m));
			});

		return dto;
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
		
		final Entry entryF = entryRepository.save(entry);
		
		if (judges.size() > count) {
			value = "Judge successfully assigned";
		}
		
		final Timestamp now = new Timestamp(System.currentTimeMillis());
		entryF.getJudges().forEach(j -> {
			
			Tabulator t = new Tabulator();
			t.setCreatedDate(now);
			t.setEntry(entryF);
			t.setFinal(false);
			t.setEvent(entryF.getEvent());
			t.setUpdatedDate(now);
			t.setJudge(j);
			
			final Tabulator tabF = tabulatorRepository.save(t);
		
			List<Criteria> criteria = criteriaRepository.findByEventId(entryF.getEvent().getId());
			criteria.forEach(c -> {
				
				Score s = new Score();
				s.setTabulator(tabF);
				s.setUpdatedDate(now);
				s.setCreatedDate(now);
				s.setCriteria(c);
				s.setDone(false);
				s.setScore(c.getMinValue());
				
				scoreRepository.save(s);
				
				
			});
		});

		return value;
	}
	
	@Override
	public String finalizeEntries(long eventId, long judgeId) {		
			
		List<Tabulator> tabulators = tabulatorRepository.findByEvent_IdAndJudge_userId(eventId, judgeId);
		
		System.out.println("tabulators " + tabulators);
		
		tabulators.forEach(tabulator -> {			
			tabulator.setFinal(true);
			tabulatorRepository.save(tabulator);
		});
		
		return tabulators.size() + " Finalized";
		
	}

	@Override
	public String removeJudges(long entryId, List<Long> judges) {
		// TODO Auto-generated method stub
		return "Not Implemented";
	}

	@Override
	public String assignUserToEntry(long entryId, long appUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntryDTO> getEntriesByEventIdAndUserId(long eventId, long userId) {
		List<EntryDTO> dtos = new ArrayList<EntryDTO>();
		List<Entry> entries = entryRepository.findByEventIdAndJudges_userId(eventId, userId);
		entries.forEach(entry -> {
			
			EntryDTO e = dtoService.convertToDTO(entry);
			e.setMembers(new ArrayList<>());
			
			List<Member> members = entry.getMembers();
			members.forEach(m -> e.getMembers().add(dtoService.convertToDTO(m)));
			
			
			dtos.add(e);
			});
		return dtos;
	}

}
