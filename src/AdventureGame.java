import java.util.Scanner;


public class AdventureGame {
	
	/*
	 * 		Main game loop is here
	 * 		can be moved to a class like GameManager.
	 * 		
	 * 		loops through player movement and inspection of objects on map
	 * 
	 */

	private static boolean finishedTurn = false, gameOver = false;

	

	public static void main(String[] args) {
		// this is the main gameplay loop controller, can be loaded by the scene
		// and this code can be moved to a method in a class such as GameManager
		
		
		// instantiate player and keyboard for input
		Player player = new Player(0, 0);
		Scanner keyboard = new Scanner(System.in);

		// generating a test level just for demo purposes
		Level testLevel = new Level(1, 1, 1, 1, 0, "Escape room story goes here"
				, "Level 0 is very mysterious... oOoooOOoh", 20, 6);

		
	
		// string and int below for keyboard input for movement, object inspection, and other menu options
		// we implement
		String sOption;
		int iOption, iMove;		
		
		
		// loops through gameplay until player selects exit game
		while (!gameOver) 
		{
			// booleans for if a player has finished a turn moving or inspecting an object,
			// if they hit a wall/immovable object, or if they made an invalid keyboard entry.
			finishedTurn = false;
			boolean hitObject = false, invalidEntry = false;
			
			// loops until player backs out of movement, inspection menu 
			while (!finishedTurn)
			{
				testLevel.displayLevel(player.getX(), player.getY());   // displays map
				// if player made an invalid entry in the main menu, prints invalid entry below map
				if (invalidEntry)
				{
					System.out.println("Invalid entry!");
					invalidEntry = false;
				}
				System.out.println("Please choose an option: ");			// main menu options
				System.out.println("1 - Move\t 2 - Inspect \t 0 - Exit");
				// checks that next input by user is an integer
				while (!keyboard.hasNextInt())
				{
					// if user input isn't an integer, add it to a String placeholder variable
					sOption = keyboard.next();

				}
				iOption = keyboard.nextInt();	// integer has been entered succesfully and assigned 
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
								testLevel.displayLevel(player.getX(), player.getY(), hitObject);
								hitObject = false;
							}
							// checks if the plaer input was incorrect during the last loop and prints a message
							// informing them of invalid input
							else if (invalidEntry)
							{
								testLevel.displayLevel(player.getX(), player.getY());
								System.out.println("Invalid entry!");
								invalidEntry = false;
							}
							// else player didn't hit a wall or entry an invalid input so display the map normally
							else
							{
								testLevel.displayLevel(player.getX(), player.getY());
								System.out.print("\n");
							}	
							System.out.println("Move Up - 1\tDown - 2\tMove Left - 3");
							System.out.println("Right - 4\tBack a Menu - 0");
							// check player input, verifying its an integer and displays invalid entry message if it isn't
							while (!keyboard.hasNextInt())
							{
								testLevel.displayLevel(player.getX(), player.getY());
								System.out.println("Invalid entry!");
								System.out.println("Move Up - 1\tDown - 2\tMove Left - 3");
								System.out.println("Right - 4\tBack a Menu - 0");
								sOption = keyboard.next();
							}
							iMove = keyboard.nextInt();
							// if the integer input is less than 0 or greater than 4, its an invalid move
							// will print invalid entry at the start of the loop
							// (can be changed to CONSTANTS once we finalize everything
							if (iMove < 0 || iMove > 4)
							{
								invalidEntry = true;
							}
							// else if entry is 0, go back a menu
							else if(iMove == 0)
							{
								back= true;
							}
							// else player made an correct input
							else
							{
								// if the player is able to move in the input direction
								if (testLevel.checkMove(iMove, player.getX(), player.getY()))
								{
									// move player
									player.move(iMove);
									// check the players new location for a trigger (will discuss this at next meeting)
									if(testLevel.checkTrigger(player.getX(), player.getY()))
									{
										System.out.println("Press enter to continue...");
										keyboard.nextLine();	// waits for the user to hit enter so longer story elements can be read before continuing
									    keyboard.nextLine();   // needs 2 next lines to work properly, quirk of java operating on multiple OS cmd line?
									}

								}
								// if the player isn't able to move, hitObject is true and will print text informing them they hit a wall
								else {
									hitObject = true;
								}
							}
						}
					
					finishedTurn = true; // if the player backs out of move menu, finished turn is true
										// takes the player back to the main level menu where they can
										// choose to move again, inspect an object, or exit
				}
				// if the player wants to inspect an object (would like this to only show up when the player is standing
				// on the tile of an object
				else if (iOption == 2)
				{
					finishedTurn = true;
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
		System.exit(0);
	} 
	
}
