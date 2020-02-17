import java.util.ArrayList;

public class Door {
	private boolean lock;
	
	public Door() {
		lock = true;
	}
	
	public boolean getLock() {
		return lock;
	}
	public void setLock(boolean l) {
		this.lock = l;
	}
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
}
