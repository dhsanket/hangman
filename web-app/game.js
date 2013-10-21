$(function(){
	$( document ).ready(function() {

		$(".alphabet").each(function(index){
			$( this ).click(function(){			
				var currentId = $( this ).attr("id");
				console.log( index + ": " + $( this ).text() );
				sendGuess(currentId);
				console.log(currentId);
			});
		});

		function sendGuess(alphabet) {
			console.log(alphabet);
			$.ajax({	
				type: 'POST',
				url: 'http://localhost:8080/hangman/game.html',
		        dataType: 'json',
				data: {guessedChar: alphabet},
				error: function(){
					console.log("Error, your request was not sent");
				},
				success: function(){ 
					processGame(success); 
				},
			}).done(function(){
				// Reload
				//location.reload();

			});
		}

		function processGame(game) {
			JSON.stringify(game);
		}
 
	});
});
