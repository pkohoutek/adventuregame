package scripts;
/**
 * Wall Class 
 * represents a wall object that is not traversable by
 * the player. Used to add variation to levels and to guide
 *  the player to goals 
 */	
public class Wall {

	// config parameters, x/y position, and the String sprite representation
	private int xPos = 0;
	private int yPos = 0;
	private String sSprite;
	
	
	/**
	 * 	Wall constructor. Non-interactive prop, think walls, columns, etc can be replaced
	 *  with a image (sprite) when we move to graphical.
	 * @param x int of walls x position on game map
	 * @param y int of walls y position on game map
	 */
	public Wall(int x, int y) {
		xPos = x;
		yPos = y;
		sSprite = "#";
	}
	
	/**
	 * copy constructor
	 * @param wall to copy
	 */
	public Wall(Wall wall)
	{
		this.xPos = wall.xPos;
		this.yPos = wall.yPos;
		this.sSprite = wall.sSprite;
	}
	
	/**
	 * getter method for Wall x value
	 * @return int of wall's x value on game map
	 */
	public int getX() {
		return xPos;
	}
	
	/**
	 * getter method for Wall y value
	 * @return int of wall's y value on game map
	 */
	public int getY() {
		return yPos;
	}
	
	/**
	 * getter method for the String representation of the wall on the game map
	 * @return String of wall object for game map
	 */
	public String getSprite() {
		return sSprite;
	}
	
}
