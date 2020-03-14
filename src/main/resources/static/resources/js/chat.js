$(document).ready(function() {
	'use strict';
	var form = $('#form');
	var stompClient = null;
	connect();
	
	form.submit(function(e) {
		e.preventDefault();
		var form = $(this);
		$.ajax({
			type	: 'POST',
			cache	: false,
			url		: form.attr('action'),
			data	: form.serialize(),
			success	: function(data) {
				
				var message = $('.myMessage').val();
				var response = '<div  class="media w-50 mb-3 ml-auto"><div class="media-body ml-3"><div class="bg-primary rounded py-2 px-3 mb-2"> <p class="text-small mb-0 text-white">'+ message +'</p></div><p class="small text-muted">'+ getMessageDate() +'</p></div></div>';
				if( !(message.length === 0 || !message.trim())){
					$('#send').append(response);
				}
				sendMessage(message);
				e.target.reset();
				divscrolldown();			
			}
		});
	});
	
	function connect() {
		var socket = new SockJS('/distribution');
        stompClient = Stomp.over(socket);
	    stompClient.connect({}, onConnected);
	}
	
	function onConnected(frame){
	        stompClient.subscribe('/topic/distribution' , onMessageReceived);
	}
	
	function onMessageReceived(payload){
		var roomId = $("#idRoom").text();
		var userId = $("#userId").text();
		
		var message = JSON.parse(payload.body);
		if(!(message.sender == userId)){
			if((message.roomId == roomId)){
				console.log(message);
				var response = '<div class="media w-50 mb-3 no"><div class="media  mb-3 no"><img alt="user" width="50" class="rounded-circle" src="/resources/users_images/'+message.profile+'"><div class="media-body ml-3"><div class="rounded py-2 px-3 mb-2 bg-light"><p class="text-small mb-0 text-muted">'+message.message +'</p></div><p class="small text-muted text-capitalize">'+message.username+'</p></div></div>';
				$('#send').append(response);
			}
		}
		 
	}
	
	function sendMessage(message) {
		
		var roomId = $("#idRoom").text();
		var userId = $("#userId").text();
		var profile = $("#userProfile").text();
		var username = $("#username").text();
		var chatMessage = {
		       sender: userId,
		       message: message,
		       roomId : roomId,
		       profile : profile,
		       username : username
		};
		 
	    stompClient.send("/app/chat.send", {}, JSON.stringify(chatMessage));
	    
	    console.log("Message send sucess !")
	}
	
	
	function divscrolldown() {
	    setTimeout(function () {
	        $('#place').animate({
	            scrollTop: $("#send").height()
	        }, 500);

	    }, 200)};
	    
	function getDate(){
		var d = new Date();
		var month = d.getMonth()+1;
		var day = d.getDate();
		var output = d.getFullYear() + '-' +
		    (month<10 ? '0' : '') + month + '-' +
		    (day<10 ? '0' : '') + day;
		return output;
	}
	
	function getMessageDate(){
		var d = new Date();
		var output = d.getHours()+" : "+d.getMinutes();
		return output;
	}
});
