package scripts;
/**	
 * Player Class
 * Handles player avatar position on map,
 * used to interact with objects in the Level class.
 * Also handles string representation of the player avatar on map
 */
public class Player {
		
	private int xPos = 0;
	private int yPos = 0;
	private int xRangeMin;
	private int xRangeMax;
	private Animator animator;
	private String sSprite;
	
	
	/**
	 * constructor takes x/y position
	 * @param x int for player's starting x position
	 * @param y int for player's starting y position
	 */
	public Player(int x, int y) {
		xPos = x;
		xRangeMin = xPos - 3;
		xRangeMax = xPos + 3;
		yPos = y;
		animator = new Animator();
		sSprite = "<(^.^)>"; 
	}
	
	/**
	 * Moves player if map location is clear
	 * returns true if the player can move and false otherwise
	 * @param move Move enum representing player's direction
	 */
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
		
	/**
	 * getter for player x position
	 * @return int of player's x position on map
	 */
	public int getX() {
		return xPos;
	}
	
	/**
	 * getter for player y position on map
	 * @return int of player's y position on map
	 */
	public int getY() {
		return yPos;
	}
	
	/**
	 * Setter method for players X position (when moving to a new level).
	 * Can/should be used for other events in game that might teleport
	 * or move the player avatar.
	 * @param x int representing x position to place the player avatar on map
	 */
	public void setX(int x) {
		xPos = x;
		xRangeMin = xPos - 3;
		xRangeMax = xPos + 3;
	}
	
	/**
	 * Setter method for players X position (when moving to a new level).
	// Can/should be used for other events in game that might teleport
	// or move the player avatar.
	 * @param y int representing y position to place player avatar on map
	 */
	public void setY(int y) {
		yPos = y;
	}
	
	/**
	 * getter for player avatars max X value
	 * @return int representing the max x position of the player avatar string
	 */
	public int getMaxX() {
		return xRangeMax;
	}
	
	/**
	 * getter for player avatar's min X value
	 * @return int representing the min x position of the player avatar string
	 */
	public int getMinX() {
		return xRangeMin;
	}
	
	/**
	 * getter for player String representation of their sprite
	 * @return String representing player avatar on map
	 */
	public String getSprite(){
		return sSprite;
	}
	
	/**
	 * Setter method updates player String representation of their sprite
	 * @param move Move enum representing the player's movement selection
	 */
	public void hitAnimation(Move move){
		sSprite = animator.getSprite(move);
	}
	
	/**
	 * Setter method for players String representation of their Sprite
	 * @param move Move enum representing the player's movement selection
	 */
	private void setSprite(Move move){
		sSprite = animator.getSprite(move);
	}
	
}
