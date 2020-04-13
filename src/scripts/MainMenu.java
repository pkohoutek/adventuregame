package scripts;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


/**	Main Menu
 *  Static class handles loading a new game (scene/level 1),
 * 	it can load saved games through SceneManager, and it is the exit
 *  point of the game.  * 
 */
public class MainMenu {

	private boolean endGame = false;
	private final String TITLE =

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


	private Scanner keyboard;
	private Game game;

	
	/**
	 * method displays the main options are: start a new game, 
	 * continue their last game, or exiting the game
	 */
	public void menu() {
		game = new Game();
		boolean invalid = false;
		int ans = 0, lvlChoice = 0;
		keyboard = new Scanner(System.in);

		while(!(ans ==1 || ans==2 || ans ==3 || ans ==99)){
			clearConsole();
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
			if (!(ans == 1 || ans == 2 || ans == 3 || ans == 99)) {
				invalid = true;
			}
			
		}
		
		if(ans == 1) {
			SceneManager.setScene(1);
			game.play();
		}
		else if(ans==2) {
			SceneManager.loadGame();
			game.play();
		}
		else if(ans ==3) {
			endGame = true;
		}
		// level select menu for testing (cheat menu)
		else if(ans ==99) {
			while(!(lvlChoice == 1 || lvlChoice == 2 || lvlChoice == 3)){
				clearConsole();
				System.out.println(TITLE);
				if (invalid) {
					System.out.print("\nPlease enter one of the options bellow: \n");
				}
				else {
					System.out.println("\n");
				}
				System.out.println("Welcome to the cheat menu, select a level:\n1 - level 1\t2 - level 2\t3 - level 3");

				try {
					lvlChoice = keyboard.nextInt();
				}
				catch (InputMismatchException e){
					invalid = true;
					keyboard.next();
				}
				if (!(lvlChoice == 1 || lvlChoice == 2 || lvlChoice == 3)) {
					invalid = true;
				}				
			}
			
			if (lvlChoice == 1)
			{
				SceneManager.setScene(1);
				game.play();
			}
			else if (lvlChoice == 2)
			{
				SceneManager.setScene(2);
				game.play();
			}
			else if (lvlChoice == 3)
			{
				SceneManager.setScene(3);
				game.play();
			}
			
		}
	}	
	
	/**
	 * getter method to display the Game's Title screen
	 * @return String of the Game Title in ASCII art
	 */
	public String getGameTitle() {
		return TITLE;
	}
	
	/**
	 * method used to exit the game
	 * @return boolean true if user input is to end the game
	 */
	public boolean exitGame() {
		return endGame;
	}

	/**
	 * clearConsole method clears terminal/cmd and checks 
	 * Operating system and runs the revelant commmand.
	 * It is used to improve immersion and user experience.
	 * 
	 * credit for code goes to Abhishek Kashyap & community at Stack Overflow.
	 * URL: 
	 * 	https://stackoverflow.com/questions/2979383/java-clear-the-console
	 */
	private final void clearConsole() {
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
