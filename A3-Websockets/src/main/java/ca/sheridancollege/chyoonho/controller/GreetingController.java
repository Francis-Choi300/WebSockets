package ca.sheridancollege.chyoonho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import ca.sheridancollege.chyoonho.messages.UsernameOut;
import ca.sheridancollege.chyoonho.messages.UsernameMessage;

@Controller
public class GreetingController {

	//receives JSON from client, sent to /app/hello
	@MessageMapping("/hello")
	//responds by sending new Greeting to /topic/greetings
	@SendToUser("/topic/greetings")
	public UsernameOut greet(@Payload UsernameMessage hellomsg)  throws Exception {
		
		Thread.sleep(1000);
		
		System.out.println("Payload HelloMessage: " + hellomsg.getName());
		return new UsernameOut("Connected To the Server As: " + hellomsg.getName());
	}
	
}
