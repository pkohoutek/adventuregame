import java.io.IOException;
import java.util.Scanner;


public class AdventureGame {
	
	/*		Adventure Game
	 * 		Main function calls MainMenu.menu() to begin game,
	 * 		and loops until the user chooses to exit the game.
	 * 		Moving to JavaFX in the future we felt it best to keep 
	 * 		most of the code out of the class containing main()
	 * 		
	 */


	public static void main(String[] args) {
		
		// while the player hasn't exited the game
		while(!MainMenu.exitGame())
		{
			MainMenu.menu();
		}
		GameOver.thanksScreen();
		System.exit(0);
	}	
}
