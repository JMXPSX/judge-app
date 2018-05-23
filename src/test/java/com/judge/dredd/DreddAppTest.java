package com.judge.dredd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.judge.dredd.model.AppUser;
import com.judge.dredd.model.Category;
import com.judge.dredd.model.Criteria;
import com.judge.dredd.model.Entry;
import com.judge.dredd.model.Event;
import com.judge.dredd.model.Member;
import com.judge.dredd.model.enums.UserType;
import com.judge.dredd.repository.AppUserRepository;
import com.judge.dredd.repository.CategoryRepository;
import com.judge.dredd.repository.CriteriaRepository;
import com.judge.dredd.repository.EntryRepository;
import com.judge.dredd.repository.EventRepository;
import com.judge.dredd.repository.MemberRepository;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DreddApp.class)
public class DreddAppTest {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	AppUserRepository appUserRepository;

	@Autowired
	EventRepository eventRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CriteriaRepository criteriaRepository;

	@Autowired
	EntryRepository entryRepository;

	@Autowired
	MemberRepository memberRepository;
	
	@Test
	public void testSave() {

		List<AppUser> list = new ArrayList<>();
		list.add(new AppUser(0l, "tony", bCryptPasswordEncoder.encode("tony"), UserType.APP_ADMIN.ordinal()));
		list.add(new AppUser(0l, "steve", bCryptPasswordEncoder.encode("steve"), UserType.APP_JUDGE.ordinal()));
		list.add(new AppUser(0l, "peter", bCryptPasswordEncoder.encode("peter"), UserType.APP_ADMIN.ordinal()));
		list.add(new AppUser(0l, "thanos", bCryptPasswordEncoder.encode("thanos"), UserType.APP_ADMIN.ordinal()));

		appUserRepository.saveAll(list);

		Event event = new Event(0l, "Avengers Assembly", new Date(), new Date(2018, 6, 4),
				appUserRepository.findByUsername("tony"));

		event = eventRepository.save(event);

		List<Category> categories = new ArrayList<>();
		categories.add(new Category(0l, "UP", "The quick Brown Fox", event));
		categories.add(new Category(0l, "DLSU", "Jumps over the Lazy dog", event));
		categories.add(new Category(0l, "FEU", "Near the bank of", event));
		categories.add(new Category(0l, "UST", "The river", event));

		categoryRepository.saveAll(categories);

		List<Criteria> criteria = new ArrayList<>();
		criteria.add(new Criteria(0l, "Strength", "Total stregnth of a superhero", 0, 10, event));
		criteria.add(new Criteria(0l, "Skills", "Skills of a superhero", 0, 10, event));
		criteria.add(new Criteria(0l, "Intelligence", "Intelligence of a superhero", 0, 10, event));
		criteria.add(new Criteria(0l, "Power", "Power of a superhero", 0, 10, event));

		criteriaRepository.saveAll(criteria);


		List<Entry> entries = new ArrayList<>();
		entries.add(new Entry(0l, "Power Rangers", "5 Human superheroes", event, categoryRepository.findByName("UP")));
		entries.add(new Entry(0l, "Thor", "God superhero", event, categoryRepository.findByName("DLSU")));
		entryRepository.saveAll(entries);
		
		Entry entry1 = entryRepository.findById(1l).get();
		List<Member> members1 = new ArrayList<>();
		members1.add(new Member(0l, "Juan", "Red", "UP",entry1));
		members1.add(new Member(0l, "Pedro", "blue", "UP",entry1));
		members1.add(new Member(0l, "Simon", "black", "UP",entry1));

		memberRepository.saveAll(members1);
		
		Entry entry2 = entryRepository.findById(2l).get();
		List<Member> members2 = new ArrayList<>();
		members2.add(new Member(0l, "jane", "jane", "DLSU",entry2));
		members2.add(new Member(0l, "loki", "loki", "DLSU",entry2));
		members2.add(new Member(0l, "chris", "chris", "DLSU",entry2));

		memberRepository.saveAll(members2);
		
		


	}
}
