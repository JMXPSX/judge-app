package com.judge.dredd.controller;

import java.sql.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.judge.dredd.dto.CommentsDTO;

@Controller
@EnableScheduling
public class PushController {

	private int array[] = new int[] { 1, 2, 3, 4 };
	private String words[] = new String[] { "Nice Entry", "Booo!", "Magnificent", "Unrealistic" };

	int index = 0;

	@MessageMapping("/comment")
	@SendTo("/topic/comments")
	@Scheduled(fixedRate=5000)
	public CommentsDTO greeting() throws Exception {
		CommentsDTO comments = new CommentsDTO();
		comments.setComment(words[index]);
		comments.setAppUserId(array[index]);
		comments.setCommentDate(new Date(0, 10, 2018));
		
		if (index == 5) {
			index = 0;
		} else {
			index++;
		}
		return comments;
	}

}