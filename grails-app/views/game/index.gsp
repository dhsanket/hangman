
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title> don't hang! :-) </title>

	</head>
	<body>
		<style type="text/css">
		#guessWord { position: relative; font-size: 24px; margin-left: 20px;}
		#result {margin-top:3px;}
		#badGuesses {}
		</style>

		The quick and easy Hangman Game 
		<p>built with Grails on the backend and HTML/js on the front </p>
		<p> Upload Form: <br />
			    <g:uploadForm action="upload">
			        <input type="file" name="myFile" />
			        <input type="submit" />
			    </g:uploadForm>
		</p>
		<button> <g:link controller="game" action="newGameButton"> New Game  </g:link> </button>  
		<div> You are allowed 6 mistakes </div>
		<br />
			<p>
			<a href="#" class="alphabet" id="a" >A</a> 
			<a href="#" class="alphabet" id="b" >B</a> 
			<a href="#" class="alphabet" id="c" >C</a> 
			<a href="#" class="alphabet" id="d" >D</a>
			<a href="#" class="alphabet" id="e" >E</a> 
			<a href="#" class="alphabet" id="f" >F</a> 
			<a href="#" class="alphabet" id="g" >G</a> 
			<a href="#" class="alphabet" id="h" >H</a>
			<a href="#" class="alphabet" id="i" >I</a>
			<a href="#" class="alphabet" id="j" >J</a> 
			<a href="#" class="alphabet" id="k" >K</a> 
			<a href="#" class="alphabet" id="l" >L</a>

			<a href="#" class="alphabet" id="m" >M</a> 
			<a href="#" class="alphabet" id="n" >N</a> 
			<a href="#" class="alphabet" id="o" >O</a> 
			<a href="#" class="alphabet" id="p" >P</a>
			<a href="#" class="alphabet" id="q" >Q</a> 
			<a href="#" class="alphabet" id="r" >R</a> 
			<a href="#" class="alphabet" id="s" >S</a> 
			<a href="#" class="alphabet" id="t" >T</a>
			<a href="#" class="alphabet" id="u" >U</a> 
			<a href="#" class="alphabet" id="v" >V</a> 
			<a href="#" class="alphabet" id="w" >W</a> 
			<a href="#" class="alphabet" id="x" >X</a>

			<a href="#" class="alphabet" id="y" >Y</a> 
			<a href="#" class="alphabet" id="z" >Z</a>
			</p>

	<div id="guessWord"> "___ _____" </div>
	<br />
	<div> Game Status: 
	<div id="result"> </div> </div>
	<br />
	<div> List of wrong Guesses; If you get more the 6 wrong Guesses, you lose the game.
	<div id="badGuesses"> </div> </div>

	
	</body>
</html>

