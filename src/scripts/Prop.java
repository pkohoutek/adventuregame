package scripts;
	
	/**
	 * 		Prop Class
	 * 		creates objects on the map for the player to interact with.
	 * 		Typically in game development props are non-interactive but due to
	 * 		the limitation of a text game props are interactive to add more atmosphere to game
	 */
	
public class Prop {
	
	private int xPos = 0;
	private int yPos = 0;

	private String sSprite = "";
	private String description  = "Put object descriptive text here."
			+ "\n eg. \"You look at an old oak bookcase, you note that it is"
			+ " full of books on the ocult.";



	/**
	 *  prop coping constructor
	 * @param prop to copy
	 */
	public Prop(Prop prop)
	{
		xPos = prop.getX();
		yPos = prop.getY();
		sSprite = prop.getSprite();
		description = prop.getDescription();	
	}
	
	
	/**
	 * Prop constructor
	 * @param des String of descriptive text of prop object
	 * @param x int representing props x position on game map
	 * @param y int representing props y position on game map
	 */
	public Prop(String des, int x, int y) {
		xPos = x;
		yPos = y;
		description = des;
		sSprite = "$";		
	} 
	
	
	/**
	 * getter method for prop description string
	 * @return String of prop description/story text
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * public helper method to print descriptive text to console
	 */
	public void printDescription()
	{
		System.out.println(description);
	}
		
	/**
	 * getter method for String representation of the sprite when displaying on map
	 * @return String of Prop object for map
	 */
	public String getSprite() {
		return sSprite;
	}
	
	/**
	 *  getter for prop x value
	 * @return int of props x position on map
	 */
	public int getX() {
		return xPos;
	}
	
	/**
	 * getter for prop y value
	 * @return int of props y position on map
	 */
	public int getY() {
		return yPos;
	}

	
}
