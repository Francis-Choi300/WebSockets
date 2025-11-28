/*  
	DESTINATIONS
	/app/hello : client sends their "username" to the server
	
	SUBSCRIPTIONS
	"/topic/greetings" : Receives their username in return
	"/topic/duck" : emits the message "duck" at frequent intervals
*/


// can also use ws = new webSocket('ws://localhost:8080/ws')
 
var stompClient = new StompJs.Client({    brokerURL: 'ws://localhost:8080/ws' });
var username = "";

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
	$( "#duck").click(()=> quack());
	$( "#goose").click(()=> goose());
	$( "#msgbtn").click(()=> sendChatMessage());
});

function connect() {
    stompClient.activate();
	setConnected(true);
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

//called when Connect/Disconnect button is pressed
//hides/displays the chat box
//clears the message log
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#display").show();
    }
    else {
       // $("#display").hide();
		$("#status").html("Currently Disconnected:");
    }
    $("#messages").html("");
}

/////////////////////TOPIC 1 : GREETINGS ///////////////////////////////////

// when connected to the Server, automatically subscribes to '/topic/greetings'
// the subscription receives a message from the client when the client sends a username to '/app/hello'
stompClient.onConnect = (frame) => {
   
    console.log('Connected Now: ' + frame);
    
	stompClient.subscribe('/user/topic/greetings', (greeting) => {
        
		var body = JSON.parse(greeting.body);
		
		showStatus(body.content);
		
		console.log("Receiving From Server Your Name: " , body);
    	
	});
	

	stompClient.subscribe('/topic/messages', (message) => {
	        var msgBody = JSON.parse(message.body);
		
			console.log("Receiving From Server Message: " , msgBody);
			addMessage(msgBody.now.substring(11,19), msgBody.username , msgBody.message  );
	});
	
}

function sendName() {
	
	$("#loggedIn").show();
	
    stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify({'name': $("#name").val()})
    });
	
	$("#display").show();
	console.log("SENT NAME:", JSON.stringify({ name: $("#name").val() }));
	
	username = $("#name").val();
}

function showStatus(message) {
    $("#status").html(message);
}


function addMessage(time,user,mess){
	$("#messages").append("<tr><td>" +time+ "</td><td>" + user + "</td><td>" + mess + "</td></tr>");
}
///////TOPIC 2: Sending Messages /////

function sendChatMessage(){

	stompClient.publish({
	    destination: "/app/chat",
	    body: JSON.stringify({
	        username: username,
	        message: $("#message").val()
	    })
	});
		console.log("SENT MESSAGE:" , JSON.stringify({'username':username,  'message': $("#message").val() }) )
		
}

///////TOPIC 3: DUCKS///////
//when button is pressed will subscribe to '/topic/duck'
function quack(){
	
	console.log("connecting to duck");
	
	addMessage("&#129414;&#129414;&#129414;&#129414;&#129414;&#129414;&#129414;&#129414;", 
		       "ClientSide" , "Subscribing to Ducks!!!  ")

	
	stompClient.subscribe('/topic/duck', (message1) => 
		{ 
			let msgBody = JSON.parse(message1.body);
			
			console.log ("Receving Duck" , msgBody);  
		    addMessage(msgBody.now.substring(11,19), msgBody.username , msgBody.message  );
		});
	
}

function goose(){
	console.log("GOOSE!");
	setConnected(false);
	disconnect();
	addMessage("", "ClientSide","Goose!");
	
}
