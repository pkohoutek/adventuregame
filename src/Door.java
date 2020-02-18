
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
	
	public boolean isLocked() {
		return lock;
	}
	
	public boolean isVertical() {
		return vertical;
	}
	
	public void unLock() {
		this.lock = false;
	}
	
	public  boolean isOpen() {
		return open;
	}
	
	public void openDoor() {
		open = true;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public String getSprite() {
		return sSprite;
	}
	
}
