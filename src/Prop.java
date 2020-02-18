
public class Prop {
	
	
	/*
	 * 		Prop Class
	 * 		creates objects on the map for the player to interact with.
	 * 		Typically in game development props are non-interactive but due to
	 * 		the limitation of a text game props are interactive to add more atmosphere to game
	 */
	
	private int xPos = 0;
	private int yPos = 0;

	private String sSprite = "";
	private String description  = "Put object descriptive text here."
			+ "\n eg. \"You look at an old oak bookcase, you note that it is"
			+ " full of books on the ocult.";



	// prop coping constructor
	public Prop(Prop prop)
	{
		xPos = prop.getX();
		yPos = prop.getY();
		sSprite = prop.getSprite();
		description = prop.getDescription();	
	}
	
	
	// story prop / trigger
	// can be split and moved to a child classes
	public Prop(String des, int x, int y) {
		xPos = x;
		yPos = y;
		description = des;
		sSprite = "$";		
	} 
	
	
	// gets prop description string
	public String getDescription() {
		return description;
	}
	
	// prints prop description to screen
	public void printDescription()
	{
		System.out.println(description);
	}
		
	// gets the String representation of the sprite (can change type to Sprite when we move to JavaFX),
	// for scalability
	public String getSprite() {
		return sSprite;
	}
	
	// get prop x value
	public int getX() {
		return xPos;
	}
	
	// get prop y value
	public int getY() {
		return yPos;
	}
	
	

	
}
