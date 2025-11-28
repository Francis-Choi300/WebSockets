package ca.sheridancollege.chyoonho.controller;

import java.time.Instant;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import ca.sheridancollege.chyoonho.messages.ChatMessage;
import ca.sheridancollege.chyoonho.messages.ChatOutgoing;

@Controller
public class ChatController {

	//receives JSON from client, sent to /app/chat
	@MessageMapping("/chat")
	//responds by sending new Greeting to /topic/messages
	@SendTo("/topic/messages")
	public ChatOutgoing chat(@Payload ChatMessage chatMsg)  throws Exception {
		
		Thread.sleep(1000);
		
		System.out.println("PayLoad UserName: " + chatMsg.getUsername());
		System.out.println("PayLoad Message: " + chatMsg.getMessage());
		
		ChatOutgoing outmsg = new ChatOutgoing( chatMsg.getUsername() , chatMsg.getMessage(), Instant.now() );
		return outmsg;
	}
}
