
public class GameClock {
	
	/*		GameClock
	 * 	Stores time at invocation at game start
	 * 	and 
	 * 
	 * 
	 */
	
	
	// time when clock is initialized at the start of the game
	// used to calculate gameover time
	private static int gStartHour;
	private static int gstartMinute;
	private static int gstartSecond;
	
	// time when game is over 
	private static int gameOverHour;
	private static int gameOverMinute;
	private static int gameOverSecond;
	
	// game timer constant
	private static final int TIMELIMIT = 20;
	
	public GameClock() {
		// put code here
		
		
	}
	
	
	public static boolean gameOver() {
		// put code here
		
		return false;
	}
	
	public static String getTimer() {
		return "20:00";
	}
	
	public static int getMinutesRemaining() {
		return 20; 
	}
	
	public static int getSecondsRemaining() {
		return 0;
	}
}
