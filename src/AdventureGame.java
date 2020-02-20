import java.io.IOException;
import java.util.Scanner;


public class AdventureGame {
	
	/*		Adventure Game
	 * 		Main function contains the basic game loop
	 * 		basic logic can be moved to a class like GameManager
	 * 		when we move to JavaFX and GUI.
	 * 		
	 * 		game() loops through player options on movement, return to title screen, 
	 * 		and inspections.
	 * 		
	 * 		Contains a clear scream method from: 
	 * 			https://stackoverflow.com/questions/2979383/java-clear-the-console
	 * 		
	 */


	public static void main(String[] args) {
		// TitleScreen.load(); class to display game title can use ascii art generator
		// MainMenu.menu(); // load main menu
		
		while(true)
		{
			MainMenu.menu();
		}
	}
	
	
	
}
