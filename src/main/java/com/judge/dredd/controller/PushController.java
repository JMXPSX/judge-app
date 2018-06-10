package com.judge.dredd.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.judge.dredd.dto.CommentsDTO;

@Controller
@EnableScheduling
public class PushController {
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	private int array[] = new int[] { 1, 2, 3, 4 };
	private String words[] = new String[] { "Nice Entry", "Booo!", "Magnificent", "Unrealistic" };

	int index = 0;

	@MessageMapping("/comment")
//	@SendTo("/topic/comments")
//	@SubscribeMapping("/topic/comments")
	@Scheduled(fixedRate=10000)
	public CommentsDTO greeting() throws Exception {
		CommentsDTO comments = new CommentsDTO();
		comments.setComment(words[index]);
		comments.setUserId(array[index]);
		comments.setCommentDate(new Date(0, 10, 2018));
//		System.out.println(comments);
		if (index == 3) {
			index = 0;
		} else {
			index++;
		}
		
		webSocket.convertAndSend("/notification", comments);
		return comments;
	}

}