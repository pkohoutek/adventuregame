import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class SceneManager {
	
	/*
	 * 		Scene Manager class
	 * 		When we move to JavaFX the methods and attributes of this class can be moved,
	 * 		to JavaFX Scene manager class (or equivalent)
	 * 
	 */

	private static int currentScene = 0;
	private static String filename = "advgame.sav";
	
	
	// load scene from argument
	public static void loadScene(int scene)
	{
		currentScene = scene;
	}
	
	// loads next scene
	public static void nextScene() {
		// if the next level exits increment to the next scene
		if (nextLevelExist(currentScene + 1))
		{
			currentScene += 1;
		}
		// else player has beaten the game
		else {
			System.out.println("\n\n\n\nCONTRATULATIONS YOU WIN");
			System.exit(0);
		}

	}
	
	// returns current scene int
	public static int getScene() {
		return currentScene;
	}
	
	// sets scene to argument value 
	public static void setScene(int scene) {
		// if the level file exists
		if (nextLevelExist(scene))
		{
			currentScene = scene;
		}
		// else an error has occured, exit the game
		else {
			System.out.println("Error! Level doesn't exist!");
			System.out.println("Exiting game.");
			System.exit(1);
		}
	}
	
	// saves game to text file
	public static void saveGame() {
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter(filename);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("An error occured opening " + filename
					+ ". Exiting game.");
			System.exit(0);
		}
		outputStream.println(currentScene);
		outputStream.close();
		
	}
	
	// loads game from text file
	public static void loadGame() {
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file. Exiting game.");
			System.exit(0);
		}
		String sScene = inputStream.nextLine();
		try 
		{
			currentScene = Integer.parseInt(sScene);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Error opening the file. Exiting game.");
			System.exit(0);
		}
		inputStream.close();
	}
		
	// method returns boolean if level .cfg file exists for the next level
	private static boolean nextLevelExist(int levelNumber) {
		boolean lExist = false;
		String filename = "level" + Integer.toString(levelNumber) + ".cfg";
		File testFile = new File(filename);
		if (testFile.exists())
		{
			lExist = true;
		}
		
		return lExist;
	}
	
	
	
	
}
