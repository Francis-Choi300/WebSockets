package ca.sheridancollege.chyoonho.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import ca.sheridancollege.chyoonho.messages.ChatOutgoing;

@Controller
public class DuckController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Scheduled(fixedRate = 2000)
	public void duck() throws Exception{
		
		ChatOutgoing duckmsg = new ChatOutgoing("Quackers","\"&#129414;\"", Instant.now());
		
		messagingTemplate.convertAndSend("/topic/duck", duckmsg );
		
		System.out.println("duck");
	}
}
