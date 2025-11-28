package ca.sheridancollege.chyoonho.messages;

public class UsernameMessage {

    private String name;

    public UsernameMessage() {}  

    public UsernameMessage(String name) {
        this.name = name;
    }

    public String getName() {   
        return name;
    }

    public void setName(String name) {   
        this.name = name;
    }
}
