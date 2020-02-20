
public class Trigger {
	
	/*
	 * 	Trigger class
	 * 	Triggers are activate when a player steps on them, this class can be expanded with
	 * 	sub classes for more fun interaction for player
	 * 
	 * 
	 */
	
	private int xPos = 0;
	private int yPos = 0;
	private boolean trigger = true;
	
	private String tText = "Put text here if you want to have story "
			+ "elements when stepping in a specific location";
	
	public Trigger(Trigger tTrigger) {
		xPos = tTrigger.getX();
		yPos = tTrigger.getY();
		tText = tTrigger.getTriggerText();
	}	
	
	public Trigger(int xStart, int yStart, String text){
		xPos = xStart;
		yPos = yStart;
		tText = text;
	}
	
	// getter for trigger string
	public String getTriggerText() {
		return tText;
	}
	
	// getter for trigger x position on map
	public int getX() {
		return xPos;
	}
	
	// getter for trigger y position on map
	public int getY() {
		return yPos;
	}
	
	// prints trigger description string
	public void printTriggerText() {
		System.out.println("\n\n\n\n" + tText);
	}
	
	// checks if prop is trigger and returns boolean value
	public boolean isTrigger() {
		return trigger;
	}
	
	// method to deactivate trigger if a trigger has been activated
	public void triggerOff() {
		trigger = false;		
	}
}
