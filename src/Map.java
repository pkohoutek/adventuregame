import java.util.Random;

public class Map {
	
	/*
	 * 	Map class builds map and hold references to game objects
	 * 	for us in levels in the game 
	 */
	
	private int xLen = 5, yLen = 5;				// map x,y dimensions
	private String[][] sMap;	// string representation of map, can add to constructors
	private int smashIndex = 0;
	private boolean[][] isTrigger = new boolean[yLen][xLen];   // boolean array to keep track of trigger locations on map
	 // string array for onomatopoeia (s.p.) words when player hits wall (i took the old 1960s batman show as inspiration)
	private final String[] SMASH = { "BONK", "BUMP", "SMASH", "CRASH", 
			"OUCH", "BANG", "BLOOP", "CLANK", "CLUNK", "KAPOW", "CRUNCH", "CRRAAAAACK",
			"OOOF", "OUCH", "OWWWW", "PLOP", "RIP", "POWIE", "SWAAP",
			"UGGGH", "WHACK", "WHAMMM", "Z-ZWAP", "WHAP", "ZAP", "ZLONK",
			"ZOWIE", "ZZZZZZWAP", "SLOSH", "SOCK", "KLONK", "KRUNCH", "KERPLOP",
			"RAKKK", "SPLATS", "SPLATT", "THUNK", "THWACK", "THWAPP"};
	private Random random;
	
	// default constructor that builds empty map with default dimensions
	public Map() {
		random = new Random();
		buildMap();
		
	}
	
	public Map(Map map)
	{
		xLen = map.getXLen();
		yLen = map.getYLen();
		random = new Random();
		sMap = map.getSMap();
		isTrigger = new boolean[yLen][xLen];
	}
	
	// constructor to build map with the x and y length as parameters
	public Map(int x, int y) {
		xLen = x;
		yLen = y;
		random = new Random();
		sMap = new String[yLen][xLen];
		isTrigger = new boolean[yLen][xLen];
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

	// prints map to console with spaces between tiles
	public void printMapSpaces(int playerX, int playerY) {
		System.out.println("\n\n\n\nLevel " + SceneManager.getScene());
		for (int x = 0; x < xLen; x++)
		{	
			if (x == 0)
			{
				System.out.print(" __");
			}
			else if (x == xLen - 1)
			{
				System.out.println("__");
			}
			else 
			{
				System.out.print("___");
			}

		}
		for (int y = yLen - 1; y >= 0; y--)
		{
			for (int x = 0; x < xLen; x++)
			{
				if (x == 0)
				{
					if (playerX == x && playerY == y)
					{
						System.out.print("| P ");
					}
					else {
						System.out.print("| " + sMap[y][x] + " ");
					}
				}	
				else if (x == xLen - 1)
				{

					if (playerX == x && playerY == y)
					{
						System.out.println("P|");
					}
					else {
						System.out.println(sMap[y][x] + "|");
					}
				}
				else if (playerX == x && playerY == y)
				{
					System.out.print(" P ");
				}
				else
				{
					System.out.print(" " + sMap[y][x] + " ");
				}				
			}
		}
		for (int x = 0; x < xLen; x++)
		{	
			if ( x == 0) {
				System.out.print("|__");
			}
			else if (x == xLen - 1)
			{
				System.out.println("__|");
			}
			else 
			{
				System.out.print("___");
			}

		}
		
	}
	
	
	// prints map with spaces between tiles and if player hit wall then prints descriptive smash text
	public void printMapSpaces(int playerX, int playerY, boolean hitWall) {
		System.out.println("\n\n\n\nLevel " + SceneManager.getScene());
		for (int x = 0; x < xLen; x++)
		{	
			if (x == 0)
			{
				System.out.print(" __");
			}
			else if (x == xLen - 1)
			{
				System.out.println("__");
			}
			else 
			{
				System.out.print("___");
			}

		}
		for (int y = yLen - 1; y >= 0; y--)
		{
			for (int x = 0; x < xLen; x++)
			{
				if (x == 0)
				{
					if (playerX == x && playerY == y)
					{
						System.out.print("| P ");
					}
					else {
						System.out.print("| " + sMap[y][x] + " ");
					}
				}	
				else if (x == xLen - 1)
				{

					if (playerX == x && playerY == y)
					{
						System.out.println("P|");
					}
					else {
						System.out.println(sMap[y][x] + "|");
					}
				}
				else if (playerX == x && playerY == y)
				{
					System.out.print(" P ");
				}
				else
				{
					System.out.print(" " + sMap[y][x] + " ");
				}				
			}
		}
		for (int x = 0; x < xLen; x++)
		{	
			if ( x == 0) {
				System.out.print("|__");
			}
			else if (x == xLen - 1)
			{
				System.out.println("__|");
			}
			else 
			{
				System.out.print("___");
			}

		}
		
		if (hitWall)
		{
			smashIndex = random.nextInt(SMASH.length - 1);
			System.out.println("*** " + SMASH[smashIndex] + "! ***");
			
		}
	}
	
	// prints map without spaces, and if player hit wall prints descriptive text
	public void printMap(int playerX, int playerY, boolean hitWall) {
		System.out.println("\n\n\n\nLevel " + SceneManager.getScene());
		for (int x = 0; x < xLen; x++)
		{	
			if (x == 0)
			{
				System.out.print(" _");
			}
			else if (x == xLen - 1)
			{
				System.out.println("_ ");
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
				if (x == 0)
				{
					if (playerX == x && playerY == y)
					{
						System.out.print("|P");
					}
					else {
						System.out.print("|" + sMap[y][x]);
					}
				}	
				else if (x == xLen - 1)
				{

					if (playerX == x && playerY == y)
					{
						System.out.println("P|");
					}
					else {
						System.out.println(sMap[y][x] + "|");
					}
				}
				else if (playerX == x && playerY == y)
				{
					System.out.print("P");
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
				System.out.print("|_");
			}
			else if (x == xLen - 1)
			{
				System.out.println("_|");
			}
			else 
			{
				System.out.print("_");
			}

		}
		
		if (hitWall)
		{
			smashIndex = random.nextInt(SMASH.length - 1);
			System.out.println("*** " + SMASH[smashIndex] + "! ***");
			
		}
	}
	
	// prints map without spaces
	public void printMap(int playerX, int playerY) {
		System.out.println("\n\n\n\nLevel " + SceneManager.getScene());
		for (int x = 0; x < xLen; x++)
		{	
			if (x == 0)
			{
				System.out.print(" _");
			}
			else if (x == xLen - 1)
			{
				System.out.println("_ ");
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
				if (x == 0)
				{
					if (playerX == x && playerY == y)
					{
						System.out.print("|P");
					}
					else {
						System.out.print("|" + sMap[y][x]);
					}
				}	
				else if (x == xLen - 1)
				{

					if (playerX == x && playerY == y)
					{
						System.out.println("P|");
					}
					else {
						System.out.println(sMap[y][x] + "|");
					}
				}
				else if (playerX == x && playerY == y)
				{
					System.out.print("P");
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
				System.out.print("|#");
			}
			else if (x == xLen - 1)
			{
				System.out.println("#|");
			}
			else 
			{
				System.out.print("#");
			}

		}
		
	}	
	
	
	// checks if players next move is good, returns true if so, false otherwise
	public boolean canMove(int move, int playerX, int playerY) {
		
		boolean canMove = false;
		
		// check to make sure a player isn't trying to walk into bounds of map
		switch(move){
			case 1:
				canMove = playerY < yLen - 1? true:false;
				if (canMove) // if not at the top of the map
				{
					// check map for immovable objects
					for (int y = 0; y < yLen; y++)
					{
						for (int x = 0; x < xLen; x++)
						{
							// if the player wants to move up to an wall/non traversable object
							if (y < yLen - 1)
								if (sMap[y + 1][x].equalsIgnoreCase("#") && playerX == x && playerY == y)
								{
									canMove = false;
								}
						}
					}
				}
				break;
			case 2:
				canMove = playerY > 0? true:false;
				if (canMove) // if not at the bottom of the map
				{
					// check map for immovable objects
					for (int y = 0; y < yLen; y++)
					{
						for (int x = 0; x < xLen; x++)
						{
							// if the player wants to move up to an wall/non traversable object
							if (y > 0)
								if (sMap[y - 1][x].equalsIgnoreCase("#") && playerX == x && playerY == y)
								{
									canMove = false;
								}
						}
					}
				}
				break;
			case 3:
				canMove = playerX > 0? true:false;
				if (canMove) // if not at the left end of the map
				{
					// check map for immovable objects
					for (int y = 0; y < yLen; y++)
					{
						for (int x = 0; x < xLen; x++)
						{
							// if the player wants to move up to an wall/non traversable object
							if (x >= 1)
								if (sMap[y][x - 1].equalsIgnoreCase("#") && playerX == x && playerY == y)
								{
									canMove = false;
								}
						}
					}
				}
				break;
			case 4:
				canMove = playerX < xLen - 1? true:false;
				if (canMove) // if not at the right end of the map
				{
					// check map for immovable objects
					for (int y = 0; y < yLen; y++)
					{
						for (int x = 0; x < xLen; x++)
						{
							// if the player wants to move up to an wall/non traversable object
							if (x < xLen - 1)
								if (sMap[y][x + 1].equalsIgnoreCase("#") && playerX == x && playerY == y)
								{
									canMove = false;
								}
						}
					}
				}
				break;
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
		if (prop.isTrigger())
		{
			isTrigger[prop.getY()][prop.getX()] = true;
		}
	}
	
	
	// simple map x length getter
	public int getXLen(){
		return xLen;
	}
	
	// simple map y length getter
	public int getYLen() {
		return yLen;
	}
	
	public String[][] getSMap()
	{
		String[][] tempArray = sMap.clone();
		return tempArray;
	}
	
}
