
public class Prop {
	
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
	
	// non-interactive prop, think walls, columns, etc can be replaced with a image (sprite) when we move to graphical
	// can be moved to a child class using extends
	public Prop(int x, int y) {
		xPos = x;
		yPos = y;
		puzzle = false;
		sSprite = "#";
	}
	
	
	// story prop / trigger
	// can be split and moved to a child classes
	public Prop(String des, int x, int y, boolean isTrigger) {
		xPos = x;
		yPos = y;
		puzzle = false;
		if (isTrigger)
		{
			trigger = true;
			triggerText = des;
			description = "";
			sSprite = " ";			
		}
		else {
			description = des;
			sSprite = "$";
		}		
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
	
	public void printDescription() {
		System.out.println(description);
	}
	public void printTriggerText() {
		System.out.println(triggerText);
	}
	
	public void printPuzzle() {
		System.out.println(sPuzzle);
	}
	
	public boolean checkAnswer(String answer) {
		boolean result = answer == sAnswer? true:false;
		return result;
	}
	
	public String getSprite() {
		return sSprite;
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public boolean isTrigger() {
		return trigger;
	}
	
	public void triggerOff() {
		trigger = false;		
	}
	
}
