$(function(){
	$( document ).ready(function() {
		
		$(".alphabet").each(function(index){
			$( this ).click(function(){			
				var currentId = $( this ).attr("id");
				sendGuess(currentId);
				//console.log(currentId);
			});
		});

		function sendGuess(alphabet) {
			console.log(alphabet);
			$.ajax({	
				type: 'POST',
				url: '/hangman/game/checkGuess',
		        dataType: 'json',
				data: {guessedChar: alphabet},
				error: function(){
					console.log("Error, your request was not sent");
				},
				success: function(data){ 
					processGame(data); 
				},
			}).done(function(){
				// Reload
				//location.reload();

			});
		}

		function processGame(gameJson) {			
			//alert(JSON.stringify(gameJson));
			$("#guessWord").html(gameJson.guessWord);
			console.log(gameJson.guessWord)
			$("#result").html(gameJson.result);
			console.log(gameJson.result)
			$("#badGuesses").html(gameJson.badGuesses);
			console.log(gameJson.badGuesses)			

		}
 
	});
});
