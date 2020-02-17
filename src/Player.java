
public class Player {
	
	/*	Player Class
	 * 	Handles player avatar position
	 * 	Interacts with objects on the level map
	 */
	
	private final int MAXKEYS = 5;
	private String[] keys = new String[MAXKEYS];  // variable we can use for puzzles solved to check if door should be opened
	private int xPos = 0;
	private int yPos = 0;
	private int xRangeMin;
	private int xRangeMax;
	private Animator animator;
	private String sSprite;
	
	
	// constructor takes x/y position
	public Player(int x, int y) {
		xPos = x;
		xRangeMin = xPos - 3;
		xRangeMax = xPos + 3;
		yPos = y;
		animator = new Animator();
		sSprite = "<(^.^)>"; 
	}
	
	
	
	// moves player if map location is clear
	// returns true if the player can move and false otherwise
	// boolean can change if not necessary
	public void move(int move) {
		switch(move) {
		case 1:				// player moves up
			yPos += 1;
			setSprite(move);
			break;
		case 2:				// player moves down
			yPos -= 1;
			setSprite(move);
			break;
		case 3:				// player moves left
			xPos -= 1;
			xRangeMin -= 1;
			xRangeMax -= 1;
			setSprite(move);
			break;
		case 4:				// player moves right
			xPos += 1;
			xRangeMin += 1;
			xRangeMax += 1;
			setSprite(move);
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
	
	public int getMaxX() {
		return xRangeMax;
	}
	
	public int getMinX() {
		return xRangeMin;
	}
	
	public String getSprite(){
		return sSprite;
	}
	
	public void hitAnimation(int move, boolean hitObject){
		sSprite = animator.getSprite(move, hitObject);
	}
	
	private void setSprite(int move){
		sSprite = animator.getSprite(move);
	}
	
}
