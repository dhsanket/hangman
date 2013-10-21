package com.hangman

import com.hangman.Game

class RunGameService {
	def tempDir = System.properties.getAt("java.io.tmpdir")
	
    def serviceMethod() {

    }
	
	def Game startGame(){
		Game game = getGame()
		return game
	}
			
	def Game checkGuess(char guessedChar, game){
		// Game2 game = getGame()
		log.error "$game.challengeWord <-- $guessedChar"
		int charLocation = game.challengeWord.indexOf((int)guessedChar)
		log.error "char location $charLocation"
		if (charLocation != -1){
			game = populateGoodGuesses(game, guessedChar)
			log.error "populated Good Guesses $game.goodGuesses"
//			return game
		}
		else {
			game = populateBadGuesses(game, guessedChar)
			log.error "populated bad Guesses $game.badGuesses"
//			return game
		}
	return game
	}
	
//	def Game checkGameStatus(game){
//		if (game.challengeWord.equals(new String(game.guessWord))){
//			game.result = Game.ResultType.WON
//		}
//		else if(game.badGuesses != null && game.badGuesses.length > 6){
//			 game.result = Game.ResultType.LOST
//		}
//		
//	return game	
//	}	
		
	private Game populateGoodGuesses(game, char guessedChar){
		if(game.goodGuesses == null){ game.goodGuesses = new char[1]
			game.goodGuesses[0] = guessedChar
		}
		else { game.goodGuesses = Arrays.copyOf(game.goodGuesses, game.goodGuesses.length+1)
			game.goodGuesses[game.goodGuesses.length-1] = guessedChar
		}
		int index = 0
		while(index > -1){
			log.error "searching for guessed char from index $index"
			index =   game.challengeWord.indexOf((int)guessedChar, index);
			if(index != -1){
				game.guessWord[index] = guessedChar
				index++
			}
		}
	return game	
	}
	
	private Game populateBadGuesses(game, char guessedChar){
		if(game.badGuesses == null){ game.badGuesses = new char[1];
			game.badGuesses[0] = guessedChar
		}
		else { game.badGuesses = Arrays.copyOf(game.badGuesses, game.badGuesses.length+1)
			game.badGuesses[game.badGuesses.length-1] = guessedChar
		}
	return game	
	}
	

	
	private Game getGame(){
		Game game		
		game = new Game(randomChallengePicker())
		return game
	}
	
	private String randomChallengePicker(){
		def challenges = []
		File f = new File(tempDir+File.separator+'challengeSamples.txt')
		
		if (f.exists()){			
			f.withReader { reader ->
				String line = reader.readLine() 
					while ( line != null){
						challenges = line
					}
			}
		} else { challenges = [ "Iron Man", "The Shard", "Nexmo", "Paris", "Coffee", "Java Beans", "California", "London" ] }
		 
		int randomIndex = new Random().nextInt(challenges.size())		
		
		return challenges[randomIndex]
	}
}
