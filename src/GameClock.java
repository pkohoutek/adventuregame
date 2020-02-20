
public class GameClock {
	
	/*		GameClock
	 * 	Stores time at invocation at game start
	 * 	and 
	 * 
	 * 
	 */
	
	
	// time when clock is initialized at the start of the game
	// used to calculate gameover time
	private int gStartHour;
	private int gstartMinute;
	private int gstartSecond;
	
	// time when game is over 
	private int gameOverHour;
	private int gameOverMinute;
	private int gameOverSecond;
	
	// game timer constant
	private final int TIMELIMIT = 20;
	
	public GameClock() {
		// put code here
		
		
	}
	
	
	public boolean gameOver() {
		// put code here
		
		return false;
	}
	
	public String getTimer() {
		return "20:00";
	}
	
}
