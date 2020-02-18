import java.util.ArrayList;

public class Level {
	
	// level number of object
	private int levelNumber;
	// array lists for the levels props and puzzles, and story text (can be changed, just testing things)
	private ArrayList<Prop> props;
	private ArrayList<Wall> walls = new ArrayList<Wall>(); // walls for level design (can remove?)
	private ArrayList<Puzzle> puzzles = new ArrayList<Puzzle>();
	private ArrayList<Cipher> ciphers;
	private ArrayList<Trigger> triggers; 
	private ArrayList<String> storyText = new ArrayList<String>();   // elements to be added
	private ArrayList<String> description = new ArrayList<String>(); // elements to be added
	private Map map;
	private Door door;
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
		puzzles = levelGenerator.getPuzzles();
		door = levelGenerator.getDoor();
		ciphers = levelGenerator.getCiphers();
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
		for (int x = playerXMin; x <= playerXMax; x++)
		{
			// if the door is directly to the left of the player
			if (x == door.getXPos() - 1 && playerY == door.getYPos())
			{
				interact = true;
			}
			// if the door is directly to the right of the player
			else if (x == door.getXPos() + 1 && playerY == door.getYPos())
			{
				interact = true;
			}
			// if the door is directly above the player
			else if (x == door.getXPos() && playerY == door.getYPos() + 1)
			{
				interact = true;
			}
			// if the door is directly below the player
			else if (x == door.getXPos() && playerY == door.getYPos() - 1 && !door.isLocked())
			{
				interact = true;
			}
		}
		// check if the player is in contact with any props
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
		// check if player is in contact with any puzzles
		for (int num = 0; num < puzzles.size(); num++)
		{
			for (int x = playerXMin; x <= playerXMax; x++)
			{
				if (x == puzzles.get(num).getX() &&
						playerY == puzzles.get(num).getY() 
						&& !puzzles.get(num).isSolved())
				{
					interact = true;
				}
			}
		}
		// check if player is in contact with any ciphers
		for (int num = 0; num < ciphers.size(); num++)
		{
			for (int x = playerXMin; x <= playerXMax; x++)
			{
				if (x == ciphers.get(num).getX() &&
						playerY == ciphers.get(num).getY() 
						&& !ciphers.get(num).isSolved())
				{
					interact = true;
				}
			}
		}
		return interact;
	}
	
	// method to interact with objects that the player is in contact with
	public void interaction(int playerXMin, int playerXMax, int playerY)
	{
		// to avoid printing trigger multiple times
		boolean activated = false;
		// loop through the player characters x range
		for (int x = playerXMin; x <= playerXMax; x++)
		{
			// if the door is directly to the left of the player
			if (x == door.getXPos() - 1 && playerY == door.getYPos())
			{
			    if (!door.isLocked()) {
			    	door.openDoor();
					System.out.println("End of Level");
			    }
			    else {
			    	System.out.println("Door is locked!");
			    }
			}
			// if the door is directly to the right of the player
			else if (x == door.getXPos() + 1 && playerY == door.getYPos())
			{
			    if (!door.isLocked()) {
			    	door.openDoor();
					System.out.println("End of Level");
			    }
			    else {
			    	System.out.println("Door is locked!");
			    }
			}
			// if the door is directly above the player
			else if (x == door.getXPos() && playerY == door.getYPos() + 1)
			{
			    if (!door.isLocked()) {
			    	door.openDoor();
					System.out.println("End of Level");
			    }
			    else {
			    	System.out.println("Door is locked!");
			    }
			}
			// if the door is directly below the player
			else if (x == door.getXPos() && playerY == door.getYPos() - 1 && !door.isLocked())
			{
			    if (!door.isLocked()) {
			    	door.openDoor();
					System.out.println("End of Level");
			    }
			    else {
			    	System.out.println("Door is locked!");
			    }
			}
		}
		// look through props for prop for interacting with player
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
		// look through levels puzzles to find a puzzle to interact with player
		for (int num = 0; num < puzzles.size(); num++)
		{
			for (int x = playerXMin; x <= playerXMax; x++)
			{
				if (x == puzzles.get(num).getX() &&
						playerY == puzzles.get(num).getY() 
						&& !puzzles.get(num).isSolved())
				{
					puzzles.get(num).doPuzzle();
					// if puzzle has been solved
					if (puzzles.get(num).isSolved())
					{
						// clear the puzzles location on the map
						map.clearSquare(puzzles.get(num).getX(), puzzles.get(num).getY());
						// check if the puzzles and ciphers to unlock door have been solved
						tryToOpenLock();
						// if door is no longer locked print message to player to let them know they
						// have completed the level objectives
						if (!isDoorLocked())
						{
							System.out.println("The door has unlocked!");
						}
					}
				}
			}	
		}
		// look through levels ciphers to find a cipher to interact with player
		for (int num = 0; num < ciphers.size(); num++)
		{
			for (int x = playerXMin; x <= playerXMax; x++)
			{
				if (x == ciphers.get(num).getX() &&
						playerY == ciphers.get(num).getY() 
						&& !ciphers.get(num).isSolved())
				{
					ciphers.get(num).playCipher();
					// if cipher has been solved
					if (ciphers.get(num).isSolved())
					{
						// clear the cipher location on the map
						map.clearSquare(ciphers.get(num).getX(), ciphers.get(num).getY());
						// if all ciphers and puzzles have been solved the door will unlock
						tryToOpenLock();
						// if door is no longer locked print message to player to let them know they
						// have completed the level objectives
						if (!isDoorLocked())
						{
							System.out.println("The door has unlocked!");
						}
					}
				}
			}
		}
	}
	
	// method to check all puzzles and ciphers in level
	// and if all puzzles have been solved unlock the door
	private void tryToOpenLock()
	{
		boolean locked = false;
		for (int num = 0; num < puzzles.size(); num++)
		{
			if (!puzzles.get(num).isSolved())
			{
				locked = true;
			}
		}
		for (int num = 0; num < ciphers.size(); num++)
		{
			if (!ciphers.get(num).isSolved())
			{
				locked = true;
			}
		}
		
		if (locked == false)
		{
			door.unLock();
		}
	}
	
	public boolean isDoorLocked() {
		return door.isLocked();
	}
	
	public boolean isDoorOpen() {
		return door.isOpen();
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

