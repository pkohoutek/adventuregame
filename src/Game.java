import java.io.IOException;
import java.util.Scanner;

public class Game {
	
	/*		Game
	 * 		Static class contains the basic game loop
	 * 	  		
	 * 		play() loops through the initial level setup and calls the gameLoop method.
	 * 
	 * 		gameLoop() loops of the initial level menu where the player can choose to enter the movement menu
	 * 		or exit the game.
	 * 
	 *  	moveMenu() provides the player options on movement, return to title screen, and inspection of game objects
	 *  	contained in the level.
	 * 			
	 * 		getDirection(int iMove, boolean hitObject) is used to pass the Move enum to the player for movement on the map
	 * 		and the players "animator" to change their avatar state based on direction of movement or if they collided with a 
	 * 		wall or object
	 * 
	 * 		checkGameOver() is called at points when the player is prompted for input or completing actions to check the 
	 * 		GameClock to determine if the player has run out of game time.	 * 
	 * 		
	 * 		Contains a clearConsole method from: 
	 * 			https://stackoverflow.com/questions/2979383/java-clear-the-console
	 * 		
	 */
	
	// booleans for various gameplay loops of the Game Class
	private static boolean finishedTurn, gameOver, back, timeSet;
	private static boolean startOfLevel, hitObject, invalidEntry;
	private static int levelNum, iOption;
	private static String sOption;
	private static Level level;
	private static Scanner keyboard;
	private static Player player;
	
	
	public static void play() {
		// this is the main gameplay loop controller, can be loaded by the scene
		// and this code can be moved to a method in a class such as GameManager
		
		
		// instantiate level, player, and keyboard for input
		level = new Level(SceneManager.getScene());
		player = new Player(level.getStartX(), level.getStartY());
		levelNum = SceneManager.getScene();
		keyboard = new Scanner(System.in);
		// set booleans for outer game loop to false
		gameOver = false;
		// set start of level to true to print the level introduction text
		startOfLevel = true;
		// set timeSet to false to trigger a reset of the timer for a new game
		// or to gather the timer from a saved game
		timeSet = false;
		// loops through game play until player selects exit game
		while (!gameOver) 
		{
			// if we have just started the level print the level intro text
			if (startOfLevel) {
				clearConsole();
				System.out.println("\n" + level.getIntroText());
				System.out.print("\n\n\nPress enter to continue...");
				keyboard.nextLine();	// waits for the user to hit enter so longer story elements can be read before continuing
				startOfLevel = false;
			}
			// finished turn boolean controls if a player is ready to exit the main level menu of move or exit
			finishedTurn = false;
			// if they hit a wall/immovable object, hitObject is true and will trigger in-game text and a change in the player's
 			// avatar to provide immersion and player feedback.
			hitObject = false;
			// boolean if the player made an invalid keyboard entry.
			invalidEntry = false;
			// loops until player backs out of movement, inspection menu 
			gameLoop();
			
		}
	}
	
	
	// the main level menu for the player. can select move which calls the moveMenu() method
	// or exit which takes them back to the MainMenu.
	private static void gameLoop() {
		// if the player has started a new game reset the timer
		if(levelNum == 1 && !timeSet)
		{
			GameClock.resetTime();
			timeSet = true;
		}
		// if the player is starting from a saved game, set the time remaining from their save
		else if (!timeSet){
			GameClock.setTimeRemaining(SceneManager.loadMinutes(), SceneManager.loadSeconds());
			timeSet = true;
		}
		// while the player hasn't backed out of the main level menu
		while (!finishedTurn)
		{
			clearConsole();
			level.displayLevel(player.getX(), player.getY(), player.getSprite());   // displays map
			// if player made an invalid entry in the main menu, prints invalid entry below map
			if (invalidEntry)
			{
				System.out.println("Invalid entry!");
				invalidEntry = false;
			}
			System.out.println("Please choose an option: ");			// main menu options
			System.out.println("1 - Move\t  \t 0 - Main Menu");
	
			// checks that next input by user is an integer and loops until complete
			while (!keyboard.hasNextInt())
			{
				// if user input isn't an integer, add it to a String placeholder variable
				sOption = keyboard.next();

			}
			iOption = keyboard.nextInt();	// integer has been entered successfully and assigned 
			// if option 1 selected the player wants to move
			if (iOption == 1)
			{
				back = false;
				moveMenu();
			}
			// if the player selects 0, ends play() loop and gameLoop() to exit game
			else if (iOption == 0)
			{
				finishedTurn = true;
				gameOver = true;
			}
			// user input an invalid entry
			else
			{
				invalidEntry = true;
			}
		}		
	}
	
	
	private static void moveMenu() {
		// local integer variable to store the players move selection
		int iMove = 0;
		
			// while the player doesn't select the back option to return to the main level menu
			while(!back)
			{
				// checks if player hit an object during the last movement loop
				if (hitObject)
				{	
					// prints map and adds a descriptive String to inform the player they hit a wall
					clearConsole();
					level.displayLevel(player.getX(), player.getY(), hitObject, player.getSprite());
					hitObject = false;
				}
				// checks if the player input was incorrect during the last loop and prints a message
				// informing them of invalid input
				else if (invalidEntry)
				{
					clearConsole();
					level.displayLevel(player.getX(), player.getY(), player.getSprite());
					System.out.println("Invalid entry!");
					invalidEntry = false;
				}
				// else player didn't hit a wall or entry an invalid input so display the map normally
				else
				{
					clearConsole();
					level.displayLevel(player.getX(), player.getY(), player.getSprite());
					System.out.print("\n");
				}
				System.out.println("Move Up - 1\tDown - 2\tMove Left - 3");
				// if the player is in contact with an interactive object print the "inspect" option
				if (level.canInteract(player.getMinX(), player.getMaxX(), player.getY())) {
						System.out.println("Right - 4\tInspect - 5\tBack to Menu - 0");
						checkGameOver();
				}	
				// else print the options without "Inspect"
				else{
					System.out.println("Right - 4\tBack to Menu - 0");
					checkGameOver();
				}
				// if the player has not run out of time and it is game over
				if (!gameOver)
				{
					// check player input, verifying its an integer and loops for input
					// displaying invalid entry message if it is not
					while (!keyboard.hasNextInt())
					{
						clearConsole();
						level.displayLevel(player.getX(), player.getY(), player.getSprite());
						System.out.println("Invalid entry!");
						// if the player is able to interact with an object in the level show the "inspect" option
						if (level.canInteract(player.getMinX(), player.getMaxX(), player.getY())) {
							System.out.println("Right - 4\tInspect - 5\tBack to Menu - 0");
							checkGameOver();
						}	
						// else print default movement options
						else{
							System.out.println("Right - 4\tBack to Menu - 0");
							checkGameOver();
						}
						// sOption is a place holder to hold string input that is not numeric
						sOption = keyboard.next();
					}
					// player entered a valid integer
					iMove = keyboard.nextInt();						
					
					// if the player is in contact with an interactible object and they selected the "inspect"
					// option.
					if (level.canInteract(player.getMinX(), player.getMaxX(), player.getY()) && iMove == 5)
					{		
						clearConsole();
						// interact with object
						level.interaction(player.getMinX(), player.getMaxX(), player.getY());
						checkGameOver();
						// if the player just opened the door
						if (level.isDoorOpen())
						{
							clearConsole();
							System.out.println("\n" + level.getExitText() + "\n\n\n");
							System.out.print("Press enter to continue");
							keyboard.nextLine();
							keyboard.nextLine();
							// check with SceneManager that there is a next level
							if (SceneManager.nextLevelExist())
							{
								// increment to next scene number
								SceneManager.nextScene();
								// assign new scene number to levelNum integer
								levelNum = SceneManager.getScene();
								// save the players game so they can continue from the start of the next level
								SceneManager.saveGame(GameClock.getMinutesRemaining(), GameClock.getMinutesRemaining());
								startOfLevel = true;
								// load the next level
								level = new Level(levelNum);
								// set players stating X/Y position in level
								player.setX(level.getStartX());
								player.setY(level.getStartY());
							}
							// else if there are no more scenes left the player
							// has completed the game, load the good game over screen
							else {
								gameOver = true;
								GameOver.beatGameScreen();
							}					
							// set game play loop booleans to true to return
							// to the main level menu
							finishedTurn = true;
							back = true;
	
						}
						// else the object the player interacted with is not the unlocked door
						else {
						System.out.print("\n\nPress enter to continue...");
						keyboard.nextLine();	// waits for the user to hit enter so longer story elements can be read before continuing
						keyboard.nextLine();
						clearConsole();
						}
					}
					// if the player is not in contact with an interactive object and
					// not selected a valid movement option (1 - up, 2 - down, 3 - left, 4 - right)
					// if the iMove integer is less than 0 or greater than 4, its an invalid move
					// will print invalid entry at the start of the loop
					else if (iMove < 0 || iMove > 4)
					{
						invalidEntry = true;
						checkGameOver();
					}
					// else if entry is 0, go back a menu
					else if(iMove == 0)
					{
						back = true;
						checkGameOver();
					}							
					// else player made an correct input to move 
					else
					{
						// get the Move enum direction for "animator" and to check & move 
						// the player on the map
						Move direction = getDirection(iMove, hitObject);
						// if the player is able to move in the input direction
						if (level.checkMove(direction, player.getX(), player.getY(), 
								player.getMinX(), player.getMaxX()))
						{
							// move player
							player.move(direction);
							// check the player's new position is a trigger, if it is
							// it will print the trigger text to the screen
							if(level.checkTrigger(player.getX(), player.getY()))
							{
								System.out.print("Press enter to continue...");
								keyboard.nextLine();	// waits for the user to hit enter so longer story elements can be read before continuing
							    keyboard.nextLine();   // needs 2 next lines to work properly, not sure if this is related to "\n" println(), or some other
							    						// quirk with Java, any feedback on this would be appreciated.
							}
						    checkGameOver();
	
						}
						// else the player isn't able to move, hitObject is true and will print text informing them they hit a wall
						// and change the player avatar to a hit object state 
						else {
							hitObject = true;
							direction = getDirection(iMove, hitObject);
							player.hitAnimation(direction);
							checkGameOver();
						}
					}
			}
		}					
		finishedTurn = true; // if the player backs out of move menu, finished turn is true
							// takes the player back to the main level menu where they can
							// choose to move again or exit

	}
	
	// helper method that checks if the player has gone past the time limit
	private static void checkGameOver() {
		if (GameClock.isGameOver())
		{
			finishedTurn = true;
			gameOver = true;
			GameOver.gameOverScreen();
			back = true;
		}
	}
	
	
	
	// helper method to convert the player's integer movement selection to
	// a Move enum
	private static Move getDirection(int iMove, boolean hitObject) {
		Move direction;
		// if the player is moving up or down but is blocked by an object or end of map string array
		if ((iMove == 1 || iMove == 2 ) && hitObject){
			iMove = 7;   // move 7 represents the element number that the player has hit an object moving up or down
		}
		// if the player is moving left and hit an object
		else if (iMove == 3 && hitObject){
			iMove = 5;	// move 5 represents the element number that the player has hit an object moving left
		}
		// if the player is moving right and hit an object
		else if(iMove == 4 && hitObject)
		{
			iMove = 6;	// move 6 represents the number that the player has hit an object moving right. 
		}
		switch(iMove)
		{
		case 1:
			 direction = Move.UP;
			break;
		
		case 2:
			direction = Move.DOWN;
			break;
		case 3:
			direction = Move.LEFT;
			break;
		case 4:
			direction = Move.RIGHT;
			break;
		case 5:
			direction = Move.HITLEFT;
			break;
		case 6:
			direction = Move.HITRIGHT;
			break;
		case 7:
			direction = Move.HITUPDOWN;
			break;
		case 8:
			direction = Move.IDLE;
			break;
		default:
			direction = Move.IDLE;
			break;
		}
		return direction;
	}

	//
	// clearConsole method clears terminal/cmd and checks 
	// Operating system and runs the revelant commmand.
	// It is used to improve immersion and user experience.
	//
	// credit for code goes to Abhishek Kashyap & community at Stack Overflow.
	// URL: 
	// 	https://stackoverflow.com/questions/2979383/java-clear-the-console
	// 
	public final static void clearConsole() {
	    //Clears Screen in java
	    try {
	        if (System.getProperty("os.name").contains("Windows")) {
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        }
	        else {
	            //Runtime.getRuntime().exec("clear");
	        	System.out.print("\033[H\033[2J");
	        }
	    } catch (IOException | InterruptedException ex) {}
	}
	
}
