package com.hangman

class Game {
	String challengeWord
	char[] guessWord // if guessWord == challengeWord before badGuess==7 --> Player WON!
	char[] badGuesses // (max 7) if max breached --> Player LOST!
	char[] goodGuesses
	ResultType result
	
	static enum ResultType { ONGOING, WON, LOST };
	
	
	public Game(String challenge) {
	challengeWord = challenge.toLowerCase()
	guessWord = challengeWord.toCharArray()
	for (int i=0; i<guessWord.length; i++){
	 if (guessWord[i] != ' ')
		guessWord[i] = '_'
	}
	result = ResultType.ONGOING
	}
		
}
