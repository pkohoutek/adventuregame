
public class Prop {
	
	
	/*
	 * 		Prop Class
	 * 		creates objects on the map for the player to interact with
	 * 		this class can be cleaned up, children can be created such as puzzle, trigger or w/e
	 * 		Right now generates from literals 1 wall, a puzzle like object, and an object that will just have story text
	 * 
	 * 		will need further implementation for generating levels
	 */
	
	private int xPos = 0;
	private int yPos = 0;
	private boolean puzzle = false;	// the puzzle and trigger can be moved to child classes
	private boolean trigger = true;
	private String sSprite = "";
	private String description  = "Put object descriptive text here."
			+ "\n eg. \"You look at an old oak bookcase, you note that it is"
			+ " full of books on the ocult.";
	private String triggerText = "Put text here if you want to have story "
			+ "elements when stepping in a specific location";
	
	private String sPuzzle = "Put instructions, question, riddle, etc in here.";
	private String sAnswer = "Put answer key here";
	

	// prop coping constructor
	public Prop(Prop prop)
	{
		xPos = prop.getX();
		yPos = prop.getY();
		sSprite = prop.getSprite();
		description = "";
		
		if (sSprite.equals( "$"))
		{
			description = prop.getDescription();	
		}
		else if (sSprite.equals("?"))
		{
			puzzle = true;
			sPuzzle = prop.getPuzzleText();
			sAnswer = prop.getPuzzleAnswer();
		}
	}
	
	
	// story prop / trigger
	// can be split and moved to a child classes
	public Prop(String des, int x, int y) {
		xPos = x;
		yPos = y;
		puzzle = false;
		description = des;
		sSprite = "$";		
	} 
	
	// Puzzle prop
	// can be moved to a child class and have more specific attributes
	public Prop(String des, String puz, String ans, int x, int y)
	{
		xPos = x;
		yPos = y;
		description = des;
		sPuzzle = puz;
		sAnswer = ans;
		puzzle = true; 
		sSprite = "?";
	}
	
	// gets prop description string
	public String getDescription() {
		return description;
	}
	
	public void printDescription()
	{
		System.out.println(description);
	}
	
	// prints puzzle text
	public void printPuzzle() {
		System.out.println(sPuzzle);
	}
	
	// getter for puzzle text string
	public String getPuzzleText() {
		return sPuzzle;
	}
	
	// getter for puzzle answer string
	public String getPuzzleAnswer() {
		return sAnswer;
	}
	
	// method to check players answer 
	public boolean checkAnswer(String answer) {
		boolean result = answer == sAnswer? true:false;
		return result;
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
