package scripts;
	
/**
 * 	Trigger class
 * 	Triggers are activate when a player steps on them, this class can be expanded with
 * 	sub classes for more fun interaction for player
 */
public class Trigger {

	
	private int xPos = 0;
	private int yPos = 0;
	private boolean trigger = true;
	
	private String tText = "Put text here if you want to have story "
			+ "elements when stepping in a specific location";
	
	/**
	 * Copy constructor
	 * @param tTrigger to copy
	 */
	public Trigger(Trigger tTrigger) {
		xPos = tTrigger.getX();
		yPos = tTrigger.getY();
		tText = tTrigger.getTriggerText();
	}	
	
	/**
	 * Trigger constructor
	 * @param xStart int of trigger x position on game map
	 * @param yStart int of trigger y position on game map
	 * @param text STring of text to display when trigger is activated
	 */
	public Trigger(int xStart, int yStart, String text){
		xPos = xStart;
		yPos = yStart;
		tText = text;
	}
	
	/**
	 * getter for trigger string
	 * @return String of trigger text
	 */
	public String getTriggerText() {
		return tText;
	}
	
	/**
	 * getter for trigger x position on map
	 * @return int of triggers x position on game map
	 */
	public int getX() {
		return xPos;
	}
	
	/**
	 * getter for trigger y position on map
	 * @return int of trigger's y position on game map
	 */
	public int getY() {
		return yPos;
	}
	
	/**
	 * method prints trigger description string
	 */
	public void printTriggerText() {
		System.out.println("\n" + tText);
	}
	
	/**
	 * method checks if prop is trigger and returns boolean value
	 * @return boolean true if it is a trigger
	 */
	public boolean isTrigger() {
		return trigger;
	}
	
	/**
	 * method to deactivate trigger if a trigger has been activated
	 */
	public void triggerOff() {
		trigger = false;		
	}
}
