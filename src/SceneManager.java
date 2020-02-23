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
	private final static String FILENAME = "advgame.sav";
	private final static String SLVLNUM = "<LEVELNUM>", ELVLNUM = "</LEVELNUM>", 
			SMINLEFT = "<MINLEFT>", EMINLEFT = "</MINLEFT>", SSECLEFT = "<SECLEFT>",
			ESECLEFT = "</SECLEFT>";
	
	
	// load scene from argument
	public static void loadScene(int scene)
	{
		currentScene = scene;
	}
	
	// loads next scene
	public static void nextScene() {
		// if the next level exits increment to the next scene
		if (nextLevelExist())
		{
			currentScene += 1;
		}
	}
	
	// returns current scene int
	public static int getScene() {
		return currentScene;
	}
	
	// sets scene to argument value 
	public static void setScene(int scene) {
		// if the level file exists
		if (levelExist(scene))
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
	public static void saveGame(int minutes, int seconds) {
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter(FILENAME);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("An error occured opening " + FILENAME
					+ ". Exiting game.");
			System.exit(0);
		}
		outputStream.println(SLVLNUM);
		outputStream.println(currentScene);
		outputStream.println(ELVLNUM);
		outputStream.println(SMINLEFT);
		outputStream.println(Integer.toString(minutes));
		outputStream.println(EMINLEFT);
		outputStream.println(SSECLEFT);
		outputStream.println(Integer.toString(seconds));
		outputStream.println(ESECLEFT);
		outputStream.close();
		
	}
	
	// loads game from text file
	public static void loadGame() {
		Scanner inputStream = null;
		String line = "";
		try
		{
			inputStream = new Scanner(new File(FILENAME));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file. Exiting game.");
			System.exit(0);
		}
		while (inputStream.hasNextLine())
		{
			line = inputStream.nextLine();
			line.trim();
			if (line.equals(SLVLNUM))
			{
				line = inputStream.nextLine();
				try 
				{
					currentScene = Integer.parseInt(line);
				}
				catch(NumberFormatException e)
				{
					System.out.println("Error opening the file. Exiting game.");
					System.exit(0);
				}
			}
		}
		inputStream.close();
	}
	
	public static int loadMinutes() {
		Scanner inputStream = null;
		String line = "";
		int minLeft = 0;
		
		try
		{
			inputStream = new Scanner(new File(FILENAME));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file. Exiting game.");
			System.exit(0);
		}
		while (inputStream.hasNextLine())
		{
			line = inputStream.nextLine();
			line.trim();
			if (line.equals(SMINLEFT))
			{
				line = inputStream.nextLine();
				try 
				{
					minLeft = Integer.parseInt(line);
				}
				catch(NumberFormatException e)
				{
					System.out.println("Error opening the file. Exiting game.");
					System.exit(0);
				}
			}
		}
		inputStream.close();
		return minLeft;
	}
	
	
	
	public static int loadSeconds() {
		Scanner inputStream = null;
		String line = "";
		int secLeft = 0;
		
		try
		{
			inputStream = new Scanner(new File(FILENAME));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file. Exiting game.");
			System.exit(0);
		}
		while (inputStream.hasNextLine())
		{
			line = inputStream.nextLine();
			line.trim();
			if (line.equals(SSECLEFT))
			{
				line = inputStream.nextLine();
				try 
				{
					secLeft = Integer.parseInt(line);
				}
				catch(NumberFormatException e)
				{
					System.out.println("Error opening the file. Exiting game.");
					System.exit(0);
				}
			}
		}
		inputStream.close();
		return secLeft;
	}
		
	// method returns boolean if level .cfg file exists for the next level
	public static boolean nextLevelExist() {
		boolean lExist = false;
		int nextScene = currentScene + 1;
		String levelFile = "level" + Integer.toString(nextScene) + ".cfg";
		File testFile = new File(levelFile);
		if (testFile.exists())
		{
			lExist = true;
		}		
		return lExist;
	}
	
	// method returns boolean if level .cfg file exists for the next level
	private static boolean levelExist(int lvlNum) {
		boolean lExist = false;
		String levelFile = "level" + Integer.toString(lvlNum) + ".cfg";
		File testFile = new File(levelFile);
		if (testFile.exists())
		{
			lExist = true;
		}		
		return lExist;
	}
	
	public static boolean saveExist() {
		boolean saveExist = false;
		File testFile = new File(FILENAME);
		if (testFile.exists())
		{
			saveExist = true;
		}
		
		return saveExist;
	}
	
	
	
	
}
