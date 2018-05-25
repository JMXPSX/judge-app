package com.judge.dredd;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.judge.dredd.model.Event;
import com.judge.dredd.repository.EventRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DreddApp.class)
public class Tester {

	@Autowired
	EventRepository eventRepository;
	@Test
	public void test() {
		
		List<Event> lst =eventRepository.findDistinctByEntries_judges_id(1);
		
		
		lst.forEach(i->{
			
			System.out.println(i.getEventName());
		});
	}

}
