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
		//def game = session.game 

		// log.error "$game.challengeWord"				
		//  render (contentType: "application/json") {
		//				   guessWord = word.capitalize()
		//		}
		
		//render (contentType: "text/json") {game}
        // redirect(action: "list", params: params)
		//render view:'index' model: [guessWord : word]
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
		
		log.error "$word"
		log.error "$result"
		log.error "$badGuesses"
		// log.error "$game.challengeWord"
		
		
//		  render (contentType: "application/json") {
//					       guessWord = word.capitalize() 
//						   result = result.capitalize()
//						   badGuesses = badGuesses.capitalize()
//				}			
		
		// render game as JSON
	}
    	
	def upload() {
		def f = request.getFile('myFile')
		if (f.empty) {
			flash.message = 'file cannot be empty'
			render(view: 'index')
			return
		}
//		f.transferTo(new File(tempDir+File.separator+'challengeSamples.txt'))
		def newFile = new File(tempDir+File.separator+'challengeSamples.txt')
		f.transferTo(newFile)
//		response.sendError(200, 'Done')
//		newGame()
		redirect(controller:'game', action: 'newGameButton')
	}
}
