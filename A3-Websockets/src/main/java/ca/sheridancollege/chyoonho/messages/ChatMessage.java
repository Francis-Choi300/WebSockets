package ca.sheridancollege.chyoonho.messages;

public class ChatMessage {
	 
	private String username;
	private String message;

	    public ChatMessage() {}
	    public ChatMessage(String username, String message) {
	        this.username = username;
	        this.message = message;
	    }

	    public String getUsername() {   // required
	        return username;
	    }
	    public void setUsername(String username) {   // required
	        this.username = username;
	    }
	    
	    public String getMessage() {
	    	return message;
	    }
	    public void setMessage(String message) {
	    	this.message = message;
	    }
}
