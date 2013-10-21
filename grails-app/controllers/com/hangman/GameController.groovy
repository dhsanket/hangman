package com.hangman

import org.springframework.dao.DataIntegrityViolationException


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
			
		  render (contentType: "application/json") {
						   guessWord = word.capitalize()
				}
    }
	
	def newGameButton(){
		// session.game = null
		session.game = runGameService.startGame()
		log.error " new game initiated $session.game.guessWord"
		render view:'index'		
		//	String word = new String(  session.game.guessWord)
		//	log.error "$word"
		//			render (contentType: "application/json") {
		//				guessWord = word.capitalize()
		//			}
	}
	
	def checkGuess(){
		char c = params.guessedChar.toCharArray()[0]
		def game = runGameService.checkGuess(c, session.game)
		
		if (game.challengeWord.equals(new String(game.guessWord))){
			game.result = Game.ResultType.WON
		}
		else if(game.badGuesses != null && game.badGuesses.length > 6){
			 game.result = Game.ResultType.LOST
		}

		
		session.game = game
		
		String word = new String(  game.guessWord)
		String result = game.result
		String badGuesses = new String(  game.badGuesses)
		
		log.error "Challenege word $game.challengeWord"
		log.error "guessed word $word"
		log.error "Game status $result"
		log.error "Bad guesses list $badGuesses"
		
		  render (contentType: "application/json") {
					       guessWord = word.capitalize() 
						   result = result.capitalize()
						   badGuesses = badGuesses.capitalize()
				}			
		
	}
    	
	def upload() {
		def f = request.getFile('myFile')
		if (f.empty) {
			flash.message = 'file cannot be empty'
			render(view: 'index')
			return
		}
		def newFile = new File(tempDir+File.separator+'challengeSamples.txt')
		f.transferTo(newFile)
//		response.sendError(200, 'Done')
		redirect(controller:'game', action: 'newGameButton')
	}
}
