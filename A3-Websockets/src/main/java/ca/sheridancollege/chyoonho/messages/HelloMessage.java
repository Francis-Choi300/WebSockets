package ca.sheridancollege.chyoonho.messages;

public class HelloMessage {

	private String message;
	
	public HelloMessage() {}
	public HelloMessage(String mess) {this.message = mess;}
	
	public void setMessage(String mess) {this.message = mess;}
	public String getMessage() {return this.message;}
}
