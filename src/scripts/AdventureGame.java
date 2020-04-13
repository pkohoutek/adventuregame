package scripts;

public class AdventureGame {
	
	/**		Adventure Game
	 * 		Main function calls MainMenu.menu() to begin game,
	 * 		and loops until the user chooses to exit the game.
	 * 		Moving to JavaFX in the future we felt it best to keep 
	 * 		most of the code out of the class containing main()
	 * 		@author Paul 
	 */

	
	/**
	 * 	main method of game, instantiates main menu and game over objects
	 * @param args
	 */
	public static void main(String[] args) {
		MainMenu mainMenu = new MainMenu();
		GameOver gameOver = new GameOver();
		// while the player hasn't exited the game
		while(!mainMenu.exitGame())
		{
			mainMenu.menu();
		}
		gameOver.thanksScreen();
		System.exit(0);
	}	
}
