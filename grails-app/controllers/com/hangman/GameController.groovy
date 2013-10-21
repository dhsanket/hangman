package com.hangman

import com.hangman.Game
// import org.springframework.dao.DataIntegrityViolationException


class GameController {
	def tempDir = System.properties.getAt("java.io.tmpdir")
	RunGameService runGameService


    def index() {	
		if (session.game==null){
			session.game = runGameService.startGame()
			log.error " new game initiated $session.game.guessWord"
			}
		else {
			String word = new String(  session.game.guessWord)
			log.error " session game loaded $session.game.guessWord"
		}
    }
	
	def newGameButton(){
		session.game = runGameService.startGame()
		log.error " new game initiated $session.game.guessWord"
		render view:'index'		
	}
	
	def checkGuess(){
		char c = params.guessedChar.toCharArray()[0]
		def game = runGameService.checkGuess(c, session.game)
		def listArray = Arrays.asList(game.badGuesses)
		Set<String> mySet = new HashSet<String>(listArray)
		if (game.challengeWord.equals(new String(game.guessWord))){
			game.result = Game.ResultType.WON
		}
		else if(game.badGuesses != null && mySet.size() > 6){
			 game.result = Game.ResultType.LOST
		}
		
		session.game = game
		
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
