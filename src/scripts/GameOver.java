package scripts;
// import libraries
import java.io.IOException;
import java.util.Scanner;


/**
 * GameOver Class
 * Simple class for displaying various game over state messages.
 * 	This class will be split up into individual scenes in JavaFX 	  
 * @author Paul
 */
public class GameOver {
	
	// private scanner and Strings for various game over scenarios
	private Scanner keyboard;
	private final String GAMEOVER = 
			
			"                                                \r\n" + 
			"                ('-.      _   .-')       ('-.   \r\n" + 
			"               ( OO ).-. ( '.( OO )_   _(  OO)  \r\n" + 
			"  ,----.       / . --. /  ,--.   ,--.)(,------. \r\n" + 
			" '  .-./-')    | \\-.  \\   |   `.'   |  |  .---' \r\n" + 
			" |  |_( O- ) .-'-'  |  |  |         |  |  |     \r\n" + 
			" |  | .--, \\  \\| |_.'  |  |  |'.'|  | (|  '--.  \r\n" + 
			"(|  | '. (_/   |  .-.  |  |  |   |  |  |  .--'  \r\n" + 
			" |  '--'  |    |  | |  |  |  |   |  |  |  `---. \r\n" + 
			"  `------'     `--' `--'  `--'   `--'  `------' \r\n" + 
			"                   (`-.      ('-.    _  .-')    \r\n" + 
			"                 _(OO  )_  _(  OO)  ( \\( -O )   \r\n" + 
			" .-'),-----. ,--(_/   ,. \\(,------.  ,------.   \r\n" + 
			"( OO'  .-.  '\\   \\   /(__/ |  .---'  |   /`. '  \r\n" + 
			"/   |  | |  | \\   \\ /   /  |  |      |  /  | |  \r\n" + 
			"\\_) |  |\\|  |  \\   '   /, (|  '--.   |  |_.' |  \r\n" + 
			"  \\ |  | |  |   \\     /__) |  .--'   |  .  '.'  \r\n" + 
			"   `'  '-'  '    \\   /     |  `---.  |  |\\  \\   \r\n" + 
			"     `-----'      `-'      `------'  `--' '--'  \r\n";
	
	
	private final String BEATGAME =
			" __      __                            __       __  __            __                       \r\n" + 
			"|  \\    /  \\                          |  \\  _  |  \\|  \\          |  \\                      \r\n" + 
			" \\$$\\  /  $$  ______   __    __       | $$ / \\ | $$ \\$$ _______  | $$                      \r\n" + 
			"  \\$$\\/  $$  /      \\ |  \\  |  \\      | $$/  $\\| $$|  \\|       \\ | $$                      \r\n" + 
			"   \\$$  $$  |  $$$$$$\\| $$  | $$      | $$  $$$\\ $$| $$| $$$$$$$\\| $$                      \r\n" + 
			"    \\$$$$   | $$  | $$| $$  | $$      | $$ $$\\$$\\$$| $$| $$  | $$ \\$$                      \r\n" + 
			"    | $$    | $$__/ $$| $$__/ $$      | $$$$  \\$$$$| $$| $$  | $$ __                       \r\n" + 
			"    | $$     \\$$    $$ \\$$    $$      | $$$    \\$$$| $$| $$  | $$|  \\                      \r\n" + 
			"     \\$$      \\$$$$$$   \\$$$$$$        \\$$      \\$$ \\$$ \\$$   \\$$ \\$$                      \r\n" + 
			"                                                                                           \r\n" + 
			"                                                                                           \r\n" + 
			"                                                                                           \r\n" + 
			"  ______                                            ______                                 \r\n" + 
			" /      \\                                          /      \\                                \r\n" + 
			"|  $$$$$$\\  ______   ______ ____    ______        |  $$$$$$\\ __     __   ______    ______  \r\n" + 
			"| $$ __\\$$ |      \\ |      \\    \\  /      \\       | $$  | $$|  \\   /  \\ /      \\  /      \\ \r\n" + 
			"| $$|    \\  \\$$$$$$\\| $$$$$$\\$$$$\\|  $$$$$$\\      | $$  | $$ \\$$\\ /  $$|  $$$$$$\\|  $$$$$$\\\r\n" + 
			"| $$ \\$$$$ /      $$| $$ | $$ | $$| $$    $$      | $$  | $$  \\$$\\  $$ | $$    $$| $$   \\$$\r\n" + 
			"| $$__| $$|  $$$$$$$| $$ | $$ | $$| $$$$$$$$      | $$__/ $$   \\$$ $$  | $$$$$$$$| $$      \r\n" + 
			" \\$$    $$ \\$$    $$| $$ | $$ | $$ \\$$     \\       \\$$    $$    \\$$$    \\$$     \\| $$      \r\n" + 
			"  \\$$$$$$   \\$$$$$$$ \\$$  \\$$  \\$$  \\$$$$$$$        \\$$$$$$      \\$      \\$$$$$$$ \\$$\n\n";
	
	
	
	
	private final String THANKS = 
			"___________.__                   __               _____             \r\n" + 
			"\\__    ___/|  |__ _____    ____ |  | __  ______ _/ ____\\___________ \r\n" + 
			"  |    |   |  |  \\\\__  \\  /    \\|  |/ / /  ___/ \\   __\\/  _ \\_  __ \\\r\n" + 
			"  |    |   |   Y  \\/ __ \\|   |  \\    <  \\___ \\   |  | (  <_> )  | \\/\r\n" + 
			"  |____|   |___|  (____  /___|  /__|_ \\/____  >  |__|  \\____/|__|   \r\n" + 
			"                \\/     \\/     \\/     \\/     \\/                      \r\n" + 
			"      __________.__                .__                                    \r\n" + 
			"      \\______   \\  | _____  ___.__.|__| ____    ____                      \r\n" + 
			"       |     ___/  | \\__  \\<   |  ||  |/    \\  / ___\\                     \r\n" + 
			"       |    |   |  |__/ __ \\\\___  ||  |   |  \\/ /_/  >                    \r\n" + 
			"       |____|   |____(____  / ____||__|___|  /\\___  /                     \r\n" + 
			"                          \\/\\/             \\//_____/                      \r\n";

	
	
	/**
	 * method displays game over screen if time runs out
	 */
	public void gameOverScreen() {
		clearConsole();
		keyboard = new Scanner(System.in);
		System.out.println(GAMEOVER);	
		System.out.println("Press enter to continue...");
		keyboard.nextLine();
	}
	
	/**
	 * method displays the won game screen when the player completes the game
	 */
	public void beatGameScreen() {
		clearConsole();
		keyboard = new Scanner(System.in);
		System.out.println(BEATGAME);	
		System.out.println("Press enter to continue...");
		keyboard.nextLine();
	}
	
	/**
	 * method displays a thank you screen at game exit
	 */
	public void thanksScreen() {
		clearConsole();
		System.out.println(THANKS);
	}
	
	
	/**
	 * clearConsole method clears terminal/cmd and checks 
	 * Operating system and runs the revelant commmand.
	 * It is used to improve immersion and user experience.
	 * credit for code goes to Abhishek Kashyap & community at Stack Overflow.
	 * URL: 
	 * https://stackoverflow.com/questions/2979383/java-clear-the-console
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
