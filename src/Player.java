
public class Player {
	
	/*	Player Class
	 * 	Handles player avatar position
	 * 	Interacts with objects on the level map
	 */
	
	private String[] keys = new String[4];
	private int xPos = 0;
	private int yPos = 0;
	
	
	// constructor takes x/y position
	public Player(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	
	
	// moves player if map location is clear
	// returns true if the player can move and false otherwise
	// boolean can change if not necessary
	public void move(int move) {
		switch(move) {
		case 1:				// player moves up
			yPos += 1;
			break;
		case 2:				// player moves down
			yPos -= 1;
			break;
		case 3:				// player moves left
			xPos -= 1;
			break;
		case 4:				// player moves right
			xPos += 1;
			break;
		default:			// if for some reason a value other than 1-4 gets passed as an argument,
			break;			// default is to do nothing
		}
	}
	
	// prints key array to console
	// add formatting to make it look nice
	public void viewKeys() {
		System.out.println(keys.toString());
	}
	
	// method to interact with puzzles 
	// and other games on the map
	public void interact() {
		// put code in here
	}
	
	// method to clear keys when entering
	// new level
	public void clearKeys() {
		for (int num = 0; num < keys.length; num++)
		{
			keys[num] = "";
		}
	}
	
	// get player x position
	public int getX() {
		return xPos;
	}
	
	// get player y position
	public int getY() {
		return yPos;
	}
	
}
