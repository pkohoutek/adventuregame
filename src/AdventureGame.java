import java.io.IOException;
import java.util.Scanner;


public class AdventureGame {
	
	/*		Adventure Game
	 * 		Main function calls MainMenu.menu() to begin game,
	 * 		and loops until the user chooses to exit the game.
	 * 		
	 */


	public static void main(String[] args) {
		
		while(!MainMenu.exitGame())
		{
			MainMenu.menu();
		}
		GameOver.thanksScreen();
		System.exit(0);
	}	
}
