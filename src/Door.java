import java.util.ArrayList;

public class Door {
	private boolean lock;
	private int xPos;
	private int yPos;
	private final String SPRITE = "=";
	
	
	public Door(int x, int y) {
		this.lock = true;
		this.xPos = x;
		this.yPos = y;
	}
	
	// added copy constructor
	public Door(Door door) {
		this.lock = door.getLock();
		this.xPos = door.xPos;
		this.yPos = door.yPos;
		System.out.println("xPos: " + xPos);
		System.out.println("yPos: " + yPos);
	}
	
	public boolean getLock() {
		return lock;
	}
	public void setLock(boolean l) {
		this.lock = l;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public String getSprite() {
		return SPRITE;
	}
	
	/*
	public void checkDoor(ArrayList<Puzzle> puzzles) {
		//this part loops through an ArrayList of puzzles and check to see if all of them are solved
		// assuming that there is a parent class with a boolean called solved which will turn true once the player solves that puzzle
		// if every puzzle is solved the lock will turn to false which means the door will open
		lock = false;
		for (Puzzle p:puzzles) {
			if(p.getSolved()==false) {
				lock = true;
			}
		}
	}
	
	*/
}
