import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class SceneManager {
	
	/*
	 * 		Scene Manager class
	 * 		This is WIP and not implemented into the "game demo".
	 * 		When we move to JavaFX the methods and attributes of this class can be moved,
	 * 		to JavaFX Scene manager class (or equivalent)
	 * 
	 */

	private static int currentScene = 0;
	private static String filename = "advsave1.txt";
	
	
	// load scene from argument
	public static void loadScene(int scene)
	{
		currentScene = scene;
	}
	
	// loads next scene
	public static void nextScene() {
		currentScene += 1;
	}
	
	// returns current scene int
	public static int getScene() {
		return currentScene;
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
		
	
	
	
	
}
