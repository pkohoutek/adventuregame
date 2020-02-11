
public class Player {
	
	/*	Player Class
	 * 	Handles player avatar position
	 * 	Interacts with objects on the level map
	 */
	
	private String[] keys = new String[4];
	private int xPos = 0;
	private int yPos = 0;
	
	
	public Player(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	
	
	// moves player if map location is clear
	// returns true if the player can move and false otherwise
	// boolean can change if not necessary
	public boolean move(int move) {
		switch(move) {
		case 1:
			yPos += 1;
			break;
		case 2:
			yPos -= 1;
			break;
		case 3:
			xPos -= 1;
			break;
		case 4:
			xPos += 1;
			break;
		default:
			break;
		}
		return true;
	}
	
	// prints key array to console
	// add formatting to make it look nice
	public void viewKeys() {
		System.out.println(keys.toString());
	}
	
	// method to interact with puzzles 
	// and other games on the map
	public void interact() {
		
	}
	
	// method to clear keys when entering
	// new level
	public void clearKeys() {
		for (int num = 0; num < keys.length; num++)
		{
			keys[num] = "";
		}
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
}
