package com.hangman
import groovy.json.*
import java.io.File

class GameController {
	def tempDir = System.properties.getAt("java.io.tmpdir")
	RunGameService runGameService


    def index() {	
		if (session.game==null){
			session.game = runGameService.startGame()
			}
    }
	
	def newGameButton(){
		File f = new File(tempDir+File.separator+'hangmanGameObject.txt')
		f.delete()
		session.game = runGameService.startGame()
		log.error " new game initiated $session.game.guessWord"
		render view:'index'		
	}
	
	def checkGuess(){
		char[] c = params.guessedChar.toCharArray()
		def game = runGameService.checkGuess(c[0], session.game)
		def badGuessesList = Arrays.asList(game.badGuesses)
		Set<String> mySet = new HashSet<String>(badGuessesList)
		if (game.challengeWord.equals(new String(game.guessWord))){
			game.result = Game.ResultType.WON
		}
		else if(game.badGuesses != null && mySet.size() > 6){
			 game.result = Game.ResultType.LOST
		}
		
		session.game = game
		// save game progress
		File f = new File(tempDir+File.separator+'hangmanGameObject.txt')
		f.delete()
		File g = new File(tempDir+File.separator+'hangmanGameObject.txt')
		f.createNewFile()
		log.error "file to be saved $f"
		def json = new JsonBuilder()

		json {
			"challengeWord" game.challengeWord
			"guessWord" game.guessWord
			"badGuesses" game.badGuesses
			"goodGuesses" game.goodGuesses
			"result" game.result
			}
		log.error " current game json $json"		
		g.setText(json.toString())


		
		String word = new String(  game.guessWord)
		String gameResult = game.result
		String gameBadGuesses = mySet.asType(String)
		
		log.error "Challenege word $game.challengeWord"
		log.error "guessed word $word"
		log.error "Game status $gameResult"
		log.error "Bad guesses list $gameBadGuesses"

		  render (contentType: "application/json") {
					       guessWord = word.capitalize() 
						   result = gameResult
						   badGuesses = gameBadGuesses
				}			
		
	}

}
