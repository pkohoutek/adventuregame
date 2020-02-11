import java.util.Random;

public class Map {
	
	/*
	 * 	Map class builds map and hold references to game objects
	 * 	for us in levels in the game 
	 */
	
	private int xLen = 5, yLen = 5;				// map x,y dimensions
	private String[][] sMap = new String[yLen][xLen];	// string representation of map, can add to constructors
	private int smashIndex = 0;
	private boolean[][] isTrigger = new boolean[yLen][xLen];
	private final String[] smash = { "BONK", "BUMP", "SMASH", "CRASH", 
			"OUCH", "BANG", "BLOOP", "CLANK", "CLUNK", "KAPOW", "CRUNCH", "CRRAAAAACK",
			"OOOF", "OUCH", "OWWWW", "PLOP", "RIP", "POWIE", "SWAAP",
			"UGGGH", "WHACK", "WHAMMM", "Z-ZWAP", "WHAP", "ZAP", "ZLONK",
			"ZOWIE", "ZZZZZZWAP", "SLOSH", "SOCK", "KLONK", "KRUNCH", "KERPLOP",
			"RAKKK", "SPLATS", "SPLATT", "THUNK", "THWACK", "THWAPP"};
	private Random random = new Random();
	
	
	public Map() {
		buildMap();
	}
	
	public Map(int x, int y) {
		xLen = x;
		yLen = y;
		sMap = new String[yLen][xLen];
		isTrigger = new boolean[yLen][xLen];
		buildMap();
	}
	
	private void buildMap() {
		for (int y = yLen - 1; y >= 0; y--)
		{
			for(int x = 0; x < xLen; x++)
			{
				sMap[y][x] = " ";
			}
		}
	}

	public void printMap(int playerX, int playerY) {
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
				}	/*
				else if (y == 0)
				{
					if (player.getX() == x && player.getY() == y)
					{
						System.out.print(" P ");
					}
				} */
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
	
	public void printMap(int playerX, int playerY, boolean hitWall) {
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
				}	/*
				else if (y == 0)
				{
					if (player.getX() == x && player.getY() == y)
					{
						System.out.print(" P ");
					}
				} */
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
			smashIndex = random.nextInt(smash.length - 1);
			System.out.println("*** " + smash[smashIndex] + "! ***");
			
		}
	}
	
	
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
		}
		

		return canMove;
	}
	
	
	public void addProp(Prop prop)
	{
		sMap[prop.getY()][prop.getX()] = prop.getSprite();
		if (prop.isTrigger())
		{
			isTrigger[prop.getY()][prop.getX()] = true;
		}
	}
	
	public int getXLen(){
		return xLen;
	}
	
	public int getYLen() {
		return yLen;
	}
}
