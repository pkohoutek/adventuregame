
public class Wall {

	/*
	 *      Wall Class 
	 *      represents a wall object that is not traversable by
	 *      the player
	 * 
	 */	
	
	// config parameters, x/y position, and the String sprite representation
	private int xPos = 0;
	private int yPos = 0;
	private String sSprite;
	
	
	// non-interactive prop, think walls, columns, etc can be replaced with a image (sprite) when we move to graphical
	// can be moved to a child class using extends
	public Wall(int x, int y) {
		xPos = x;
		yPos = y;
		sSprite = "#";
	}
	
	// copy constructor if needed
	public Wall(Wall wall)
	{
		this.xPos = wall.xPos;
		this.yPos = wall.yPos;
		this.sSprite = wall.sSprite;
	}
	
	// get Wall x value
	public int getX() {
		return xPos;
	}
	
	// get prop y value
	public int getY() {
		return yPos;
	}
	
	// gets the String representation of the sprite (can change type to Sprite when we move to JavaFX),
	// for scalability
	public String getSprite() {
		return sSprite;
	}
	
}
