package ca.sheridancollege.chyoonho.messages;

import java.time.Instant;

public class ChatOutgoing {
	
	private String username;
	private String message;
	private Instant now;
	
	public ChatOutgoing() {}
	public ChatOutgoing(String username, String message, Instant now) 
		{
		this.username= username;
		this.message = message;
		this.now = now;
		}
	public String getUsername() {return username;}
	public String getMessage() {return message;}
	public String getNow() {return now.toString();}
}
