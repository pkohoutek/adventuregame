
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
	
	public Trigger(Trigger trigger) {
		xPos = trigger.getX();
		yPos = trigger.getY();
		tText = trigger.getTriggerText();
	}	
	
	public Trigger(int xStart, int yStart, String text){
		xPos = xStart;
		yPos = yStart;
		tText = text;
	}
	
	public String getTriggerText() {
		return tText;
	}
	
	public int getX() {
		return xPos;
	}
	
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
