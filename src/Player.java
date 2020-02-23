
public class Player {
	
	/*	Player Class
	 * 	Handles player avatar position on map,
	 *  used to interact with objects in the Level class.
	 *  Also handles string representation of the player avatar on map
	 */
	
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
	public void move(Move move) {
		switch(move) {
		case UP:				// player moves up
			yPos += 1;
			setSprite(move);
			break;
		case DOWN:				// player moves down
			yPos -= 1;
			setSprite(move);
			break;
		case LEFT:				// player moves left
			xPos -= 1;
			xRangeMin -= 1;
			xRangeMax -= 1;
			setSprite(move);
			break;
		case RIGHT:				// player moves right
			xPos += 1;
			xRangeMin += 1;
			xRangeMax += 1;
			setSprite(move);
			break;
		default:			// if for some reason a value other than 1-4 gets passed as an argument,
			break;			// default is to do nothing
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
	
	// set players X position (when moving to a new level)
	// can/should be used for other events in game that might teleport
	// or move the player avatar
	public void setX(int x) {
		xPos = x;
		xRangeMin = xPos - 3;
		xRangeMax = xPos + 3;
	}
	
	// set players X position (when moving to a new level)
	// can/should be used for other events in game that might teleport
	// or move the player avatar
	public void setY(int y) {
		yPos = y;
	}
	
	// getter for player avatars max X value
	public int getMaxX() {
		return xRangeMax;
	}
	
	// getter for player avatar's min X value
	public int getMinX() {
		return xRangeMin;
	}
	
	// getter for player String representation of their sprite
	public String getSprite(){
		return sSprite;
	}
	
	// updates player String representation of their sprite
	public void hitAnimation(Move move){
		sSprite = animator.getSprite(move);
	}
	
	// sets players String representation of their Sprite
	private void setSprite(Move move){
		sSprite = animator.getSprite(move);
	}
	
}
