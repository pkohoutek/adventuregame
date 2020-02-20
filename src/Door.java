
public class Door {
	private boolean lock, open;
	private int xPos;
	private int yPos;
	private boolean vertical;
	private String sSprite;
	
	
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
	
	// added copy constructor
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
	
	// public method for classes to check if door is locked
	public boolean isLocked() {
		return lock;
	}
	
	// used to determine how to display door on map
	// and how to interact with door 
	public boolean isVertical() {
		return vertical;
	}
	
	// public method to unlock door
	public void unlock() {
		this.lock = false;
	}
	
	// method for classes to check if the door has been opened
	public  boolean isOpen() {
		return open;
	}
	
	// method to open door
	public void openDoor() {
		open = true;
	}
	
	// getter for doors X position
	public int getXPos() {
		return xPos;
	}
	
	// getter for doors Y position
	public int getYPos() {
		return yPos;
	}
	
	// returns the sprite for the door
	public String getSprite() {
		return sSprite;
	}
	
}
