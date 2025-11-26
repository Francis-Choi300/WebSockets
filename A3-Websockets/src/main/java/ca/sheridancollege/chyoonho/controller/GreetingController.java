package ca.sheridancollege.chyoonho.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import ca.sheridancollege.chyoonho.messages.Greeting;
import ca.sheridancollege.chyoonho.messages.HelloMessage;

@Controller
public class GreetingController {

	// /ws/hello
	// 
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greet(HelloMessage hellomsg)  throws Exception {
		
		Thread.sleep(1000);
		
		System.out.println("_______________________" + hellomsg);
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(hellomsg.getMessage()));
	}
	
	
}
