import java.util.Scanner;
public class MainMenu {
	
	/*	Main Menu
	 *  Static class handles loading a new game (scene/level 1),
	 * 	it can load saved games through SceneManager, and it is the exit
	 *  point of the game. 
	 * 
	 */

	private static final String TITLE =

			"           ____  ____   ___   __   ____  ____ \n" + 
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

	public static void menu() {
		Game.clearConsole();
		System.out.println(TITLE);
		System.out.println("\n");
		if (SceneManager.saveExist()) {
			System.out.println(" 1 - New Game\t  2 - Continue Game\t3 - Exit Game");
		}
		else {
			System.out.println(" 1 - New Game\t\t\t\t3 - Exit Game");
		}
		keyboard = new Scanner(System.in);
		int ans = keyboard.nextInt();
		if(ans == 1) {
			SceneManager.setScene(1);
			Game.play();
		}
		else if(ans==2) {
			SceneManager.loadGame();
			Game.play();
		}
		else if(ans ==3) {
			System.exit(0);
		}else {
			System.out.println("You did not enter one of the options");
			System.exit(0);
		}
	}	
	

	public static String getGameTitle() {
		return TITLE;
	}


}
