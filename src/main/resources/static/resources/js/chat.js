$(document).ready(function() {
	'use strict';

	function divscrolldown() {
	    setTimeout(function () {
	        $('#place').animate({
	            scrollTop: $("#send").height()
	        }, 500);

	    }, 200)};
	    
	$('#form').submit(function(e) {
		e.preventDefault();
		var form = $(this);
		$.ajax({
			type	: 'POST',
			cache	: false,
			url		: form.attr('action'),
			data	: form.serialize(),
			success	: function(data) {
				var d = new Date();
				var month = d.getMonth()+1;
				var day = d.getDate();
				var output = d.getFullYear() + '-' +
				    (month<10 ? '0' : '') + month + '-' +
				    (day<10 ? '0' : '') + day;
				var message = $('.myMessage').val();
				var response = '<div  class="media w-50 mb-3 ml-auto"><div class="media-body ml-3"><div class="bg-primary rounded py-2 px-3 mb-2"> <p class="text-small mb-0 text-white">'+ message +'</p></div><p class="small text-muted">'+ output +'</p></div></div>';
				if( !(message.length === 0 || !message.trim())){
					$('#send').append(response);
				}
				
				divscrolldown();
				e.target.reset();
			}
		});
	});
});
