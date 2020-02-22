import java.io.IOException;
import java.util.Scanner;

import javafx.scene.shape.MoveTo;

public class Game {
	
	/*		Game
	 * 		Static class contains the basic game loop
	 * 	 * 		
	 * 		play() loops through player options on movement, return to title screen, 
	 * 		and inspections.
	 * 		
	 * 		Contains a clearConsole method from: 
	 * 			https://stackoverflow.com/questions/2979383/java-clear-the-console
	 * 		
	 */
	

	private static boolean finishedTurn, gameOver;
	private static boolean startOfLevel;
	private static int levelNum;
	private static Level level;
	private static Scanner keyboard;
	private static Player player;
	
	
	public static void play() {
		// this is the main gameplay loop controller, can be loaded by the scene
		// and this code can be moved to a method in a class such as GameManager
		
		
		// instantiate player and keyboard for input
//		SceneManager.setScene(levelNum);
		level = new Level(SceneManager.getScene());
		player = new Player(level.getStartX(), level.getStartY());
		keyboard = new Scanner(System.in);
		GameClock.resetTime();		
	
		// string and int below for keyboard input for movement, object inspection, and other menu options
		// we implement
		String sOption;
		int iOption, iMove = 0;		
		gameOver = false;
		startOfLevel = true;
		
		// loops through game play until player selects exit game
		while (!gameOver) 
		{
			if (startOfLevel) {
				clearConsole();
				System.out.println("\n\n\n" + level.getIntroText());
				System.out.println("\n\nPress enter to continue...");
				keyboard.nextLine();	// waits for the user to hit enter so longer story elements can be read before continuing
				startOfLevel = false;
			}
			// booleans for if a player has finished a turn moving or inspecting an object,
			// if they hit a wall/immovable object, or if they made an invalid keyboard entry.
			finishedTurn = false;
			boolean hitObject = false, invalidEntry = false;
			if (GameClock.isGameOver())
			{
				finishedTurn = true;
				gameOver = true;
				GameOver.gameOverScreen();

			} 
			// loops until player backs out of movement, inspection menu 
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

				// checks that next input by user is an integer
				while (!keyboard.hasNextInt() && !gameOver)
				{
					// if user input isn't an integer, add it to a String placeholder variable
					sOption = keyboard.next();

				}
				iOption = keyboard.nextInt();	// integer has been entered successfully and assigned 
				// if option 1 selected the player wants to move
				if (iOption == 1)
				{
					// boolean to determine if the player wants to go back to the main level menu
					boolean back = false;
						// while the player doesn't select the back option to the main level menu
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
							}	
							// else print the options without "Inspect"
							else{
								System.out.println("Right - 4\tBack to Menu - 0");
							}
							// check player input, verifying its an integer and loops for input
							// displaying invalid entry message if it isn't
							while (!keyboard.hasNextInt() && !gameOver)
							{
								clearConsole();
								level.displayLevel(player.getX(), player.getY(), player.getSprite());
								System.out.println("Invalid entry!");
								if (level.canInteract(player.getMinX(), player.getMaxX(), player.getY())) {
									System.out.println("Right - 4\tInspect - 5\tBack to Menu - 0");
									if (GameClock.isGameOver())
									{
										finishedTurn = true;
										gameOver = true;
										GameOver.gameOverScreen();
										back = true;
									} 
								}	
								else{
									System.out.println("Right - 4\tBack to Menu - 0");
									if (GameClock.isGameOver())
									{
										finishedTurn = true;
										gameOver = true;
										GameOver.gameOverScreen();
										back = true;
									} 
								}
								sOption = keyboard.next();
							}
							if (!gameOver)
							{
								iMove = keyboard.nextInt();
									
								
								// if the integer input is less than 0 or greater than 4, its an invalid move
								// will print invalid entry at the start of the loop
								// (can be changed to CONSTANTS once we finalize everything
								if (level.canInteract(player.getMinX(), player.getMaxX(), player.getY()) && iMove == 5)
								{		
									clearConsole();
									level.interaction(player.getMinX(), player.getMaxX(), player.getY());
									if (level.isDoorOpen())
									{
										clearConsole();
										System.out.println("\n\n\n" + level.getExitText());
										System.out.println("\nPress enter to continue");
										keyboard.nextLine();
										if (SceneManager.nextLevelExist())
										{
											SceneManager.nextScene();
											levelNum = SceneManager.getScene();
											SceneManager.saveGame(GameClock.getMinutesRemaining(), GameClock.getMinutesRemaining());
											level = new Level(levelNum);
											player.setX(level.getStartX());
											player.setY(level.getStartY());
										}
										else {
											gameOver = true;
											GameOver.beatGameScreen();
										}
										

										finishedTurn = true;
										back = true;

									}
									else {
									System.out.println("\n\nPress enter to continue...");
									keyboard.nextLine();	// waits for the user to hit enter so longer story elements can be read before continuing
									keyboard.nextLine();
									clearConsole();
									}
								}
								// if the player is not in contact with an interactive object and
								// not selected a valid movement option (1 - up, 2 - down, 3 - left, 4 - right)
								else if (iMove < 0 || iMove > 4)
								{
									invalidEntry = true;
								}
								// else if entry is 0, go back a menu
								else if(iMove == 0)
								{
									back = true;
								}							
								// else player made an correct input
								else
								{
									Move direction = getDirection(iMove, hitObject);
									// if the player is able to move in the input direction
									if (level.checkMove(direction, player.getX(), player.getY(), 
											player.getMinX(), player.getMaxX()))
									{

										// move player
										player.move(direction);
										// check the players new location for a trigger 
										clearConsole();
										if(level.checkTrigger(player.getX(), player.getY()))
										{
											System.out.println("Press enter to continue...");
											keyboard.nextLine();	// waits for the user to hit enter so longer story elements can be read before continuing
										    keyboard.nextLine();   // needs 2 next lines to work properly, quirk of java operating on multiple OS cmd line?
										}
	
									}
									// if the player isn't able to move, hitObject is true and will print text informing them they hit a wall
									else {
										hitObject = true;
										direction = getDirection(iMove, hitObject);
										player.hitAnimation(direction);
									}
								}
							}
						}					
					finishedTurn = true; // if the player backs out of move menu, finished turn is true
										// takes the player back to the main level menu where they can
										// choose to move again or exit
				}
				// if the player selects 0, ends main menu loop and exits "GameManager" loop to exit game
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
	}
	
	
	
	
	
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
