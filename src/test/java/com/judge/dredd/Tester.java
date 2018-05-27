package com.judge.dredd;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.judge.dredd.model.AppUser;
import com.judge.dredd.model.Event;
import com.judge.dredd.repository.AppUserRepository;
import com.judge.dredd.repository.EventRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DreddApp.class)
public class Tester {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	AppUserRepository appUserRepository;
	@Test
	public void test() {
		
		List<Event> lst =eventRepository.findDistinctByEntries_judges_userId(1);
		
		
		lst.forEach(i->{
			
			System.out.println(i.getEventName());
		});
		
		List<AppUser> users = appUserRepository.findDistinctByUserTypeAndEntries_Event_Id(0, 1l);
		
		users.forEach(i -> System.out.println(i.getUsername()));
	
	System.out.println("end");
	}

}
