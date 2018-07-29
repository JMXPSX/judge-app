package com.judge.dredd;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.DreddApp;
import com.judge.dredd.model.AppUser;
import com.judge.dredd.model.Entry;
import com.judge.dredd.model.Event;
import com.judge.dredd.model.Score;
import com.judge.dredd.model.Tabulator;
import com.judge.dredd.repository.AppUserRepository;
import com.judge.dredd.repository.CriteriaRepository;
import com.judge.dredd.repository.EntryRepository;
import com.judge.dredd.repository.EventRepository;
import com.judge.dredd.repository.ScoreRepository;
import com.judge.dredd.repository.TabulatorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DreddApp.class)
public class Tester {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	AppUserRepository appUserRepository;

	@Autowired
	TabulatorRepository tb;
	@Autowired
	EntryRepository er;
	@Autowired
	CriteriaRepository cr;
	@Autowired
	ScoreRepository sr;
	
	@Test
	public void test() {

		List<Event> lst = eventRepository.findDistinctByEntries_judges_userId(1);

		lst.forEach(i -> {

			System.out.println(i.getEventName());
		});

		List<AppUser> users = appUserRepository.findDistinctByUserTypeAndEntries_Event_Id(0, 1l);

		users.forEach(i -> System.out.println(i.getUsername()));

		System.out.println("end");

		
		List<Score> scores = sr.findByCriteria_Event_idAndTabulator_Entry_entryId(1, 1);
		
		scores.forEach(i->System.out.println(i));
		System.out.println("Yeah");
		Tabulator tab = tb.findByEvent_IdAndEntry_entryId(1, 1);
		
		System.out.println(tab);
		
	}
	
//	@Test
	public void tabulatoreAndSCore() {
		
		Tabulator tab = new Tabulator();
		Entry entry = er.findById(1l).get();
		tab.setEntry(entry);
		tab.setEvent(entry.getEvent());
		tab.setJudge(appUserRepository.findById(2l).get());

		tb.save(tab);
		
		List<Score> scores = new ArrayList<>();
		scores.add(new Score(0l, cr.findById(1l).get(), 0.0, false, tab));
		scores.add(new Score(0l, cr.findById(2l).get(), 0.0, false, tab));
		scores.add(new Score(0l, cr.findById(3l).get(), 0.0, false, tab));
		scores.add(new Score(0l, cr.findById(4l).get(), 0.0, false, tab));

		sr.saveAll(scores);
	}

}
