import java.util.Random;

public class Map {
	
	/*
	 * 	Map class builds map and hold references to game objects
	 * 	for us in levels in the game 
	 * 	Level class checks using the players x,y position if they can move
	 *  on the map
	 *  uses a primitive collision system to keep player within the array bounds
	 *  and not walking through walls or doors
	 */
	
	private int xLen = 5, yLen = 5;				// map x,y dimensions
	private String[][] sMap;	// string representation of map, can add to constructors
	private int smashIndex = 0;
	private final int CHAR1 = 0, CHAR2 = 1, CHAR3 = 2, CHAR4 = 3, CHAR5 = 4, CHAR6 = 5, CHAR7 = 6;
  // boolean array to keep track of trigger locations on map
	 // string array for onomatopoeia (s.p.) words when player hits wall (i took the old 1960s Batman show as inspiration)
	private final String[] SMASH = { "BONK", "BUMP", "SMASH", "CRASH", 
			"OUCH", "BANG", "BLOOP", "CLANK", "CLUNK", "KAPOW", "CRUNCH", "CRRAAAAACK",
			"OOOF", "OUCH", "OWWWW", "PLOP", "RIP", "POWIE", "SWAAP",
			"UGGGH", "WHACK", "WHAMMM", "Z-ZWAP", "WHAP", "ZAP", "ZLONK",
			"ZOWIE", "ZZZZZZWAP", "SLOSH", "SOCK", "KLONK", "KRUNCH", "KERPLOP",
			"RAKKK", "SPLATS", "SPLATT", "THUNK", "THWACK", "THWAPP"};
	
	// using constants if we want to change the way things look for the map board
	// in an easier fashion
	private final String WALL = "#", HDOOR = "|", VDOOR = "=", LBORDER = "|#",
			RBORDER = "#|", TOPLEFT = " _", TOPRIGHT = "_ ", TBORDER = "_",
			VOUTERBORDER = "|", HOUTERBORDER = "_";
	
	private Random random;
	
	// default constructor that builds empty map with default dimensions
	public Map() {
		random = new Random();
		buildMap();
		
	}
	
	// copy constructor
	public Map(Map map)
	{
		xLen = map.getXLen();
		yLen = map.getYLen();
		random = new Random();
		sMap = map.getSMap();
	}
	
	// constructor to build map with the x and y length as parameters
	public Map(int x, int y) {
		xLen = x;
		yLen = y;
		random = new Random();
		sMap = new String[yLen][xLen];
		buildMap();
	}
	
	
	// build map method that generates the sMap String array
	// iterates backwards so that (0,0) is in bottom left corner
	private void buildMap() {
		for (int y = yLen - 1; y >= 0; y--)
		{
			for(int x = 0; x < xLen; x++)
			{
				sMap[y][x] = " ";
			}
		}
	}

	
	// prints map and if player hit wall prints descriptive text
	public void printMap(int playerX, int playerY, boolean hitWall, String sPlayer) {
		
		System.out.println("\n\n\n\nLevel " + SceneManager.getScene() +  
				"\t\t\t  Time Left: " + GameClock.getTimer());
		for (int x = 0; x < xLen; x++)
		{	
			if (x == 0)
			{
				System.out.print(TOPLEFT);
			}
			else if (x == xLen - 1)
			{
				System.out.println(TOPRIGHT);
			}
			else 
			{
				System.out.print(TBORDER);
			}

		}
		for (int y = yLen - 1; y >= 0; y--)
		{
			for (int x = 0; x < xLen; x++)
			{
				if (x == 0)
				{
					if (playerX - 3 == x && playerY == y)
					{
						System.out.print(VOUTERBORDER + sPlayer.charAt(CHAR1));
					}
					else {
						System.out.print(VOUTERBORDER + sMap[y][x]);
					}
				}	
				else if (x == xLen - 1)
				{

					if (playerX + 3 == x && playerY == y)
					{
						System.out.println(sPlayer.charAt(CHAR7) + VOUTERBORDER);
					}
					else {
						System.out.println(sMap[y][x] + VOUTERBORDER);
					}
				}
				else if (playerX - 3 == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR1));
				}
				else if (playerX - 2 == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR2));
				}
				else if (playerX - 1 == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR3));
				}
				else if (playerX == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR4));
				}
				else if (playerX + 1 == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR5));
				}
				else if (playerX + 2 == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR6));
				}
				else if (playerX + 3 == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR7));
				}
				else
				{
					System.out.print(sMap[y][x]);
				}				
			}
		}
		for (int x = 0; x < xLen; x++)
		{	
			if ( x == 0) {
				System.out.print(LBORDER);
			}
			else if (x == xLen - 1)
			{
				System.out.println(RBORDER);
			}
			else 
			{
				System.out.print(WALL);
			}

		}
		
		if (hitWall)
		{
			smashIndex = random.nextInt(SMASH.length - 1);
			System.out.println("*** " + SMASH[smashIndex] + "! ***");
			
		}
	}
	
	// prints map 
	public void printMap(int playerX, int playerY, String sPlayer) {

		System.out.println("\n\n\n\nLevel " + SceneManager.getScene() +  
				"\t\t\t  Time Left: " + GameClock.getTimer());
		for (int x = 0; x < xLen; x++)
		{	
			if (x == 0)
			{
				System.out.print(TOPLEFT);
			}
			else if (x == xLen - 1)
			{
				System.out.println(TOPRIGHT);
			}
			else 
			{
				System.out.print("_");
			}

		}
		for (int y = yLen - 1; y >= 0; y--)
		{
			for (int x = 0; x < xLen; x++)
			{
				// room has no wall boarder at left side of map print player and outer boarder
				if (x == 0)
				{
					if (playerX - 3 == x && playerY == y)
					{
						System.out.print(VOUTERBORDER + sPlayer.charAt(CHAR1));
					}
					else {
						System.out.print(VOUTERBORDER + sMap[y][x]);
					}
				}	
				// room has no wall boarder at right side of map print player and outer boarder
				else if (x == xLen - 1)
				{

					if (playerX + 3 == x && playerY == y)
					{
						System.out.println(sPlayer.charAt(sPlayer.length() - 1) + VOUTERBORDER);
					}
					else {
						System.out.println(sMap[y][x] + VOUTERBORDER);
					}
				}
				// print player on map
				else if (playerX - 3 == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR1));
				}
				else if (playerX - 2 == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR2));
				}
				else if (playerX - 1 == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR3));
				}
				else if (playerX == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR4));
				}
				else if (playerX + 1 == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR5));
				}
				else if (playerX + 2 == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR6));
				}
				else if (playerX + 3 == x && playerY == y)
				{
					System.out.print(sPlayer.charAt(CHAR7));
				}
				else
				{
					System.out.print(sMap[y][x]);
				}				
			}
		}
		for (int x = 0; x < xLen; x++)
		{	
			if ( x == 0) {
				System.out.print(LBORDER);
			}
			else if (x == xLen - 1)
			{
				System.out.println(RBORDER);
			}
			else 
			{
				System.out.print(WALL);
			}

		}
		
	}	
	
	
	// checks if players next move is good, returns true if so, false otherwise
	public boolean canMove(Move move, int playerX, int playerY, int playerXMin, int playerXMax) {
		
		boolean canMove = false;
		
		// check to make sure a player isn't trying to walk into bounds of map
		switch(move){
			// check if player can move up
			case UP:
				canMove = playerY < yLen - 1? true:false;
				if (canMove) // if not at the top of the map
				{
					int playerYNext = playerY + 1;
					// check map for immovable objects
					for (int x = playerXMin; x <= playerXMax ; x++)
					{
						// if the player wants to move up to an wall/non traversable object
						if (playerYNext <= yLen - 1)
							if (sMap[playerYNext][x].equalsIgnoreCase(WALL)
									|| sMap[playerYNext][x].equalsIgnoreCase(HDOOR))
							{
								canMove = false;
							}
					}					
				}
				break;
			// check if player can move down
			case DOWN:
				canMove = playerY > 0? true:false;
				if (canMove) // if not at the bottom of the map
				{
					int playerYNext = playerY - 1;
					// check map for immovable objects
					for (int x = playerXMin; x <= playerXMax; x++)
					{
						// if the player wants to move up to an wall/non traversable object
						if (playerYNext >= 0)
							if (sMap[playerYNext][x].equalsIgnoreCase(WALL)
									|| sMap[playerYNext][x].equalsIgnoreCase(HDOOR))
							{
								canMove = false;
							}
					}
				}
				break;
			// check if player can move left
			case LEFT:
				canMove = playerXMin > 0? true:false;
				if (canMove) // if not at the left end of the map
				{
					int playerXNext = playerXMin - 1;
					// check map for immovable objects
					if (sMap[playerY][playerXNext].equalsIgnoreCase(WALL)
						 || sMap[playerY][playerXNext].equalsIgnoreCase(HDOOR))
					{
						canMove = false;
					}				
				}
				break;
			// check if player can move right
			case RIGHT:
				canMove = playerXMax < xLen - 1? true:false;
				if (canMove) // if not at the right end of the map
				{
					int playerXNext = playerXMax + 1;
					// check map for immovable objects
					if (sMap[playerY][playerXNext].equalsIgnoreCase(WALL)
							 || sMap[playerY][playerXNext].equalsIgnoreCase(HDOOR))
					{
						canMove = false;
					}			
					
				}
				break;
			// if somehow a value outside of UP, DOWN, LEFT, RIGHT is passed through as an argument
			default:
				canMove = false;
				break;
		}
		return canMove;
	}
	
	
	// adds prop string to map, and adds trigger to boolean array
	public void addProp(Prop prop)
	{
		sMap[prop.getY()][prop.getX()] = prop.getSprite();

	}
	
	// adds walls to map
	public void addProp(Wall wall)
	{
		sMap[wall.getY()][wall.getX()] = wall.getSprite();
	}
	
	// adds door to map
	public void addDoor(Door door) {
		// if the door is vertical ie "||"
		if (door.isVertical())
		{
			// if the door object is at x position 1, print add the door "|" string to map
			// and add an addition character in the index position before.
			if (door.getXPos() == 1)
			{
				sMap[door.getYPos()][door.getXPos()] = door.getSprite();
				sMap[door.getYPos()][door.getXPos() - 1] = door.getSprite();
			}
			// if the door is at the second last x position on the map
			// add the door string to map, and add an additional string at the 
			// last index position of array
			else if (door.getXPos() == xLen - 2)
			{
				sMap[door.getYPos()][door.getXPos()] = door.getSprite();
				sMap[door.getYPos()][door.getXPos() + 1] = door.getSprite();
			}
		}
		// else the door is horizontal, ie "=", add it to the map
		else {
			sMap[door.getYPos()][door.getXPos()] = door.getSprite();
		}

	}
	
	// adds puzzle to map
	public void addPuzzle(Puzzle puzzle){
		sMap[puzzle.getY()][puzzle.getX()] = puzzle.getSprite();
	}
	
	// adds cipher puzzle to map
	public void addPuzzle(Cipher cipher) {
		sMap[cipher.getY()][cipher.getX()] = cipher.getSprite();
	}
	
	// clears array element in map (used when completed a puzzle or cipher)
	// can be used to remove walls or other objects as well for gameplay purposes
	public void clearSquare(int x, int y)
	{
		sMap[y][x] = " ";
	}
	
	// simple map x length getter
	public int getXLen(){
		return xLen;
	}
	
	// simple map y length getter
	public int getYLen() {
		return yLen;
	}
	
	// returns the string array map
	public String[][] getSMap()
	{
		String[][] tempArray = sMap.clone();
		return tempArray;
	}
	
}
