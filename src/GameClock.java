import java.util.Calendar;

public class GameClock {
	
	/*		GameClock
	 * 	Static class that keeps track of game time.
	 *  Methods return minutes and seconds remaining for
	 *  continue feature, returns a string of the remaining time
	 *  for the games map view, and is checked for for game over if the
	 *  player goes over the 20 minute time limit (for current build). 
	 * 
	 */
	
	
	// total end time in seconds
	private static int endTimeInSeconds;
	
	// game timer constant
	private static final int TIMELIMIT = 20;
	private static final int HOURCONV = 3600, MINCONV = 60;

	
	// method that checks if the player has gone over the game's time limit
	public static boolean isGameOver() {
		 int currentTimeInSeconds = getTimeInSeconds();
		 boolean gameOver = true;
		 if (currentTimeInSeconds < endTimeInSeconds)
			 gameOver = false;
		 return gameOver;
	}	
	

	// returns a string time in "00:00" format for the game's map view
	public static String getTimer()	{
		String remainingTime = "";
		int minutesRemaining = getMinutesRemaining();
		int secondsRemaining = getSecondsRemaining();		

		String mRemaining = String.format("%02d", minutesRemaining);
		String sRemaining = String.format("%02d", secondsRemaining);
		remainingTime =  mRemaining + ":" + sRemaining;

		return remainingTime;
	}
	
	// returns minutes remaining for saving game and works as helper 
	// method for getTimer()
	public static int getMinutesRemaining()	{
		int currentTimeInSeconds = getTimeInSeconds();
		int timeRemaining = endTimeInSeconds - currentTimeInSeconds;
		int minutesRemaining = timeRemaining / MINCONV;
		
		return minutesRemaining;
	}
	
	// returns seconds remaining for saving game and works as a helper
	// method for getTimer()
	public static int getSecondsRemaining()	{
		int currentTimeInSeconds = getTimeInSeconds();
		int timeRemaining = endTimeInSeconds - currentTimeInSeconds;
		int secondsRemaining = timeRemaining % MINCONV;	
		
		return secondsRemaining;
	}
	
	// returns hours remaining if needed in the future 
	public static int getHoursRemaining()	{
		int currentTimeInSeconds = getTimeInSeconds();
		int timeRemaining = endTimeInSeconds - currentTimeInSeconds;
		int hoursRemaining = timeRemaining / HOURCONV;	
		
		return hoursRemaining;
	}
	
	// private helper method to get time in seconds for calculations
	// in other methods
	private static int getTimeInSeconds() {
		Calendar currentTime = Calendar.getInstance();
		int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
		int currentMinute = currentTime.get(Calendar.MINUTE);
		int currentSecond = currentTime.get(Calendar.SECOND);
		int currentTimeInSeconds = (currentHour * HOURCONV) + (currentMinute * MINCONV) + currentSecond;
		return currentTimeInSeconds;
	}
	
	// method to set time remaining when loading from save game
	public static void setTimeRemaining(int minutes, int seconds) {
		endTimeInSeconds = (minutes * MINCONV) + seconds + getTimeInSeconds();	
	}
	
	// resets the game clock when starting a new game
	public static void resetTime() {
		int currentTimeInSeconds = getTimeInSeconds();
		endTimeInSeconds = currentTimeInSeconds + (TIMELIMIT * MINCONV);
	}
	
}
