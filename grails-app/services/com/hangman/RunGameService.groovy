package com.hangman
import groovy.json.*
import java.io.*

class RunGameService {
	def tempDir = System.properties.getAt("java.io.tmpdir")

	def Game startGame(){
		Game game = getGame()
		
		return game
	}
			
	def Game checkGuess(char guessedChar, game){
		if (game.result == Game.ResultType.ONGOING){
			log.error "$game.challengeWord <-- $guessedChar"
			int charLocation = game.challengeWord.indexOf((int)guessedChar)
			log.error "char location $charLocation"
			if (charLocation != -1){
				game = populateGuessWord(game, guessedChar)
				log.error "populated Good Guesses $game.goodGuesses"
			}
			else {
				game = populateBadGuesses(game, guessedChar)
				log.error "populated bad Guesses $game.badGuesses"
			}
		}
	return game
	}
		
	private Game populateGuessWord(game, char guessedChar){
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
		File f = new File(tempDir+File.separator+'hangmanGameObject.txt')
		log.error " saved file found $f"
		def sizeOfFile = f.size()
		def exists = f.exists()
		log.error "file exists $exists size $sizeOfFile"
			if(f.exists()){
				if(f.getText() != null && f.getText().length()>0){
//					f.delete()
					def slurper = new JsonSlurper()
					def jsonText = f.getText()
					log.error "json from file $jsonText"
					game = slurper.parseText( jsonText )
					log.error "game from savedfile $game.challengeWord"
					log.error " saved game initiated $game.guessWord"
				} else {
				game = new Game(randomChallengePicker())
				log.error " new game initiated $game.guessWord"
				}
			}	else {
				game = new Game(randomChallengePicker())
				log.error " new game initiated $game.guessWord"
			}			
		return game
	}
	
	private String randomChallengePicker(){
		def challenges = []
		challenges = [ "Iron Man", "The Shard", "Nexmo", "Paris", "Coffee", "Java Beans", "California", "London" ] 
		 
		int randomIndex = new Random().nextInt(challenges.size())		
		
		return challenges[randomIndex]
	}
}
