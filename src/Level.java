import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Level {
	
	// level number of object
	private int levelNumber = 0;
	// array lists for the levels props and puzzles, and story text (can be changed, just testing things)
	private ArrayList<Prop> props;
	private ArrayList<Prop> walls = new ArrayList<Prop>(); // walls for level design (can remove?)
	private ArrayList<Prop> puzzles = new ArrayList<Prop>();
	private ArrayList<Trigger> triggers; 
	private ArrayList<String> storyText = new ArrayList<String>();   // elements to be added
	private ArrayList<String> description = new ArrayList<String>(); // elements to be added
	private Map map;
	private LevelGenerator levelGenerator;
	private int playerStartX, playerStartY;	
	
	// constructor uses level number combined LevelGenerator to generate map, props, puzzles, and 
	// triggers from text config files.
	public Level(int level)
	{
		levelNumber = level;
		levelGenerator = new LevelGenerator(level);
		map = levelGenerator.getMap();
		props = levelGenerator.getProps();
		triggers = levelGenerator.getTriggers();
		playerStartX = levelGenerator.getPlayerStartX();
		playerStartY = levelGenerator.getPlayerStartY();
		
		
	}
	// instantiates level without puzzles, can be used for making scenes with story elements
	// no map
	public Level(int lNum,  String[] sText, String[] dText) {
		
			levelNumber = lNum;
			// iterates over String array to add story, level description text elements to level
			// can be used for specific level children where they have level specific methods
			for (int num = 0; num < sText.length; num++)
			{
				storyText.add(sText[num]);
			}
			for (int num = 0; num < dText.length; num++)
			{
				description.add(dText[num]);
			}
	}
	
	// get level number for map view
	public int getLevelNum() {
		return levelNumber;
	}
	
	public void displayLevel(int playerX, int playerY, boolean hitObject, String sPlayer) {
		map.printMap(playerX, playerY, hitObject, sPlayer);
	}
	
	// displays level without spaces between the map String "tiles"
	public void displayLevel(int playerX, int playerY, String sPlayer) {
		map.printMap(playerX, playerY, sPlayer);
	}
		
	// checks the map to see if the player can move
	public boolean checkMove(int move, int playerX, int playerY, int playerXMin, int playerXMax) {
		return map.canMove(move, playerX, playerY, playerXMin, playerXMax);
	}
	
	// checks the map to see if the player has stepped on a trigger
	public boolean checkTrigger(int playerX, int playerY)
	{
		boolean isTrigger = false;
		for (int num = 0; num < triggers.size(); num++)
		{
			if (playerX == triggers.get(num).getX() 
					&& playerY == triggers.get(num).getY() 
						&& triggers.get(num).isTrigger())
			{
				triggers.get(num).printTriggerText();
				isTrigger = true;
				triggers.get(num).triggerOff();
			}
		}		
		return isTrigger;
	}
	
	public boolean canInteract(int playerXMin, int playerXMax, int playerY)
	{
		boolean interact = false;
		for (int num = 0; num < props.size(); num++)
		{
			for (int x = playerXMin; x <= playerXMax; x++)
			{
				if (x == props.get(num).getX() &&
						playerY == props.get(num).getY())
				{
					interact = true;
				}
			}
		}
		
		for (int num = 0; num < puzzles.size(); num++)
		{
			for (int x = playerXMin; x <= playerXMax; x++)
			{
				if (x == puzzles.get(num).getX())
				{
					interact = true;
				}
			}
		}
		return interact;
	}
	
	
	public void interaction(int playerXMin, int playerXMax, int playerY)
	{
		boolean activated = false;
		for (int num = 0; num < props.size(); num++)
		{
			for (int x = playerXMin; x <= playerXMax; x++)
			{
				if (x == props.get(num).getX() &&
						playerY == props.get(num).getY() && !activated)
				{
					activated = true;
					System.out.println("\n\n\n\n" + props.get(num).getDescription());
				}
			}
		}
		
		activated = false;
		
		for (int num = 0; num < puzzles.size(); num++)
		{
			for (int x = playerXMin; x <= playerXMax; x++)
			{
				if (x == puzzles.get(num).getX())
				{
					activated = true;
					System.out.println("\n\n\n\n" + puzzles.get(num).getDescription());
				}
			}
		}
	}
	
	// simple getter for Map X length if needed
	public int mapX() {
		return map.getXLen();
	}
	
	// simple getter for Map Y lengthS if needed
	public int mapY() {
		return map.getYLen();
	}
	
	public int getStartX()
	{
		return playerStartX;
	}
	
	public int getStartY()
	{
		return playerStartY;
	}
	
	
}

