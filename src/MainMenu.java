import java.util.InputMismatchException;
import java.util.Scanner;
public class MainMenu {
	
	/*	Main Menu
	 *  Static class handles loading a new game (scene/level 1),
	 * 	it can load saved games through SceneManager, and it is the exit
	 *  point of the game. 
	 * 
	 */

	private static boolean endGame = false;
	private static final String TITLE =

			"          ____  ____   ___   __   ____  ____ \n" + 
			"          (  __)/ ___) / __) / _\\ (  _ \\(  __)\n" + 
			"           ) _) \\___ \\( (__ /    \\ ) __/ ) _) \n" + 
			"          (____)(____/ \\___)\\_/\\_/(__)  (____)\n" + 
			"                ____   __    __   _  _ \n" + 
			"               (  _ \\ /  \\  /  \\ ( \\/ )\n" + 
			"                )   /(  O )(  O )/ \\/ \\\n" + 
			"               (__\\_) \\__/  \\__/ \\_)(_/\r\n" + 
			"  __   ____  _  _  ____  __ _  ____  _  _  ____  ____ \r\n" + 
			" / _\\ (    \\/ )( \\(  __)(  ( \\(_  _)/ )( \\(  _ \\(  __)\r\n" + 
			"/    \\ ) D (\\ \\/ / ) _) /    /  )(  ) \\/ ( )   / ) _) \r\n" + 
			"\\_/\\_/(____/ \\__/ (____)\\_)__) (__) \\____/(__\\_)(____)";


	private static Scanner keyboard;

	
	// method displays the main options are: start a new game, 
	// continue their last game, or exiting the game
	public static void menu() {
		boolean invalid = false;
		int ans = 0;
		keyboard = new Scanner(System.in);

		while(!(ans ==1 || ans==2 || ans ==3)){
			Game.clearConsole();
			System.out.println(TITLE);
			if (invalid) {
				System.out.print("\nPlease enter one of the options bellow: \n");
			}
			else {
				System.out.println("\n");
			}
			if (SceneManager.saveExist()) {
				System.out.println(" 1 - New Game\t  2 - Continue Game\t3 - Exit Game");
			}
			else {
				System.out.println(" 1 - New Game\t\t\t\t3 - Exit Game");
			}

			try {
				ans = keyboard.nextInt();
			}
			catch (InputMismatchException e){
				invalid = true;
				keyboard.next();
			}
			if (!(ans == 1 || ans == 2 || ans == 3)) {
				invalid = true;
			}
			
		}
		
		if(ans == 1) {
			SceneManager.setScene(1);
			Game.play();
		}
		else if(ans==2) {
			SceneManager.loadGame();
			Game.play();
		}
		else if(ans ==3) {
			endGame = true;
		}
	}	
	

	public static String getGameTitle() {
		return TITLE;
	}
	
	public static boolean exitGame() {
		return endGame;
	}


}
