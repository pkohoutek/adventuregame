import java.util.Scanner;



public class GameOver {
	
	/*		GameOver Class
	 * 	Simple class for displaying various game over state messages.
	 * 	This class will be split up into individual scenes in JavaFX 	 * 
	 */
	
	private static Scanner keyboard;
	private static final String GAMEOVER = 
			
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
	
	
	private static final String BEATGAME =
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
			"  \\$$$$$$   \\$$$$$$$ \\$$  \\$$  \\$$  \\$$$$$$$        \\$$$$$$      \\$      \\$$$$$$$ \\$$";
	
	
	
	
	private static final String THANKS = 
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

	
	
	// method displays game over screen if time runs out
	public static void gameOverScreen() {
		Game.clearConsole();
		keyboard = new Scanner(System.in);
		System.out.println(GAMEOVER);	
		System.out.println("Press enter to continue...");
		keyboard.nextLine();
	}
	
	// method displays the won game screen when the player completes the game
	public static void beatGameScreen() {
		Game.clearConsole();
		keyboard = new Scanner(System.in);
		System.out.println(BEATGAME);	
		System.out.println("Press enter to continue...");
		keyboard.nextLine();
	}
	
	// method displays a thank you screen at game exit
	public static void thanksScreen() {
		Game.clearConsole();
		System.out.println(THANKS);
	}
		
			
			 
			
			
}
