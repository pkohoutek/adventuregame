/**
 * Door Class
 * Door is an object that represents the door and exit to the level
 * it is locked at invocation and unlocked by the player solving puzzles 
 * within the level
 * @author Paul *
 */
public class Door {
	
	

	// door config parameters
	private boolean lock, open;
	private int xPos;
	private int yPos;
	private boolean vertical;
	private String sSprite;
	
	/**
	 * Door constructor
	 * @param x int of doors x position
	 * @param y int of doors y position
	 * @param vert boolean representing if the door is vertical or horizontal on the map
	 */
	public Door(int x, int y, boolean vert) {
		this.lock = true;
		this.open = false;
		this.xPos = x;
		this.yPos = y;
		this.vertical = vert;
		if (vertical)
		{
			sSprite = "|";
		}
		else {
			 sSprite = "=";
		}
	}
	
	/**
	 * Door copy constructor
	 * @param door Door object to copy
	 */
	public Door(Door door) {
		this.lock = door.isLocked();
		this.vertical = door.isVertical();
		this.xPos = door.xPos;
		this.yPos = door.yPos;
		if (this.vertical)
		{
			sSprite = "|";
		}
		else {
			sSprite = "=";
		}
	}
	
	/**
	 * public method for classes to check if door is locked
	 * @return boolean true if the door is locked
	 */
	public boolean isLocked() {
		return lock;
	}
	
	/**
	 * used to determine how to display door on map
	 * and how to interact with door 
	 * @return boolean true if the door is vertical on the game map
	 */
	public boolean isVertical() {
		return vertical;
	}
	
	// public method to unlock door
	public void unlock() {
		this.lock = false;
	}
	
	/**
	 * getter used to determine how to display door on map
	 * and how to interact with door 
	 * @return boolean true if the door is open
	 */
	public boolean isOpen() {
		return open;
	}
	
	/**
	 * 	setter method to open door
	 */
	public void openDoor() {
		open = true;
	}
	
	/**
	 * getter method for door's x position on map
	 * @return int of door's map x position
	 */
	public int getXPos() {
		return xPos;
	}
	
	/**
	 * getter for doors Y position
	 * @return int of door's map y position
	 */
	public int getYPos() {
		return yPos;
	}
	
	/**
	 * 	getter returns the string representing the door for the map
	 * @return String of door "sprite"
	 */
	public String getSprite() {
		return sSprite;
	}
	
}
