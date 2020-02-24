# ESCAPE ROOM ADVENTURE GAME
--------------------------------------------------------------------------------------------------
## How to Compile and Run Game                                                                                           
--------------------------------------------------------------------------------------------------
### To compile:
	Copy *.java files to a folder and then run:
		
	javac AdventureGame.java
### To run:
	Place all level_.cfg and level_.map (the _ represents the level numbers) files 
	in the directory containing the compiled class files, then run:

	java AdventureGame
--------------------------------------------------------------------------------------------------
## Game Information
--------------------------------------------------------------------------------------------------

A text adventure game where you stuck are a detective trying to solve a murder mystery,
the and discover who killed Scarlett Johanson.

The game is played with the keyboard and the player needs to solve puzzles to advance in the 
game and unlock doors to proceed. The game is timed like an escape room and the player has to 
beat the game (escape the mansion) before the timer expires.

--------------------------------------------------------------------------------------------------
## Classes
--------------------------------------------------------------------------------------------------

### AdventureGame:
	
	The main class of the game. It calls MainMenu. 
	
	
### MainMenu:
	
	MainMenu is a static class and the initial “screen” the player interacts with. 
	They can choose to start a new game, continue their previous game, or exit the game.
	

### Game:
	
	Contains the basic gameplay loop and player keyboard interface. Adventure game contains 
	code to clear the console in Windows and Linux to improve immersion. It “removes” the typical 
	text scrolling when printing the command line window and keys the in-game text and map 
	interaction in the same location of the screen at all times.
	
	
### Player:
	
	Player class represents the players Avatar in the game world. It provides classes such as 
	Level and Map the players location for interaction with objects like Puzzles, Props, and 
	Ciphers, as well as collision with walls. The player is moved through this class in the 
	game world. Player has-a relationship with Animator, and Level classes.
	

### Animator:
	
	Animator class handles the player’s string “animations”. Depending on the players direction 
	and if the player has collided with an object, the animator will set the players string 
	“sprite” to represent that in the game.
	
	
### Wall:
	
	Wall class is an object that is non-interactive and works as a collider to guide the player 
	to goals and keep the player within the level’s bounds. It also provides the player with 
	level variety.
	
	
### Prop:
	Prop class represents objects in the game that typically the player does not directly 
	interact with, such as paintings on walls, furniture, plants, trees, etc. As the game is only 
	text based we have made the props interactable with the player to provide more immersion.
	
### Trigger:
	Trigger class is an invisible object contained within the Level map that is used to trigger 
	events when the player steps on its position. This class will be used to create further 
	child classes to create interactive scenes for the story and even trigger mini-games or 
	puzzles in future revisions.
	
### Puzzle:
	Interactive object on the level map. Puzzles use elements of the game’s story to challenge 
	the player into solving them. These provide the majority of the level’s challenges and 
	successfully solving these puzzles is required to advance in the game.
	
### Cipher:
	A child of the Puzzle class, Cipher’s are puzzles involving the encryption of text using bit
	shifting. Cipher’s include hints and have limited attempts at solving them.

### Door:
	The Door is an object that is invoked once per level. It is the exit for the current room. 
	The door is locked until the player progresses through the level and solves the levels puzzles 
	and ciphers. Once the levels challenges have been completed the Level class unlocks the door and
	player is notified in game that they can exit the level.
	
### Map:
	Map class uses a two-dimensional String array to keep track of objects on the level and handles
	collisions to ensure the player is within the array’s bounds. Map is created when a Level is
	invoked, and it is populated and returned to the newly instantiated level by the LevelGenerator 
	class.
	
### Level:
	Level is the class that represents the level the player is playing in. It is constructed using the 
	LevelGenerator class with returns ArrayLists containing the game objects for the level, which can 
	include props, walls, triggers, puzzles, ciphers, and doors. The LevelGenerator also returns the 
	level map and the level and map determine if a player is able to move to a position on the map.

	Level determines if a player is able to interact with a game object like a puzzle or cipher, and 
	has checks when the player has completed a level’s objective and tries to unlock the door. If all
	objectives are completed the level informs the player if the door is unlocked and the player can 
	exit the level to continue on their quest to escape and solve the mystery of Scarlett’s death.

	
### LevelGenerator:
	LevelGenerator class uses text file input to generate Levels. All map objects and Strings
	are contained in level_.cfg files (where _ is the level number), and the level’s map containing 
	character representations of these objects is also generated. These objects and strings are 
	populated into Arrays which are then returned to the Level class constructor at invocation. 
	This is to help improve level development as we can use a text editor to design the games 
	levels giving and create config files containing the attributes of the objects on the map. 
	This is modular and expandable.

### GameClock:
	GameClock class handles the timer of the game, as Escape Room’s have time limits. It also 
	has-a relationship with SceneManager as it returns the players time left and adds it to their 
	save game when they successfully advance a level. The Game class checks if there is time left 
	in the game during player’s turn and loads a GameOver class (representing the Game Over scene) 
	and returns the player back to the Main Menu.
	
### SceneManager:
	SceneManager class represents the Scene management in many common game development environments
	such Unity, Unreal Engine, GODOT, and GameMaker Studio. It is static and stores the current 
	scene (level) of the game, handles game loading, and saving, and has private methods to ensure 
	that the game is not loading to level that does not exist.
	
### GameOver:
	Simple static class that displays either the Game Over screen if the player runs out of time,
	or the Beat Game screen congratulating the player on completing the game.
	
	
	
