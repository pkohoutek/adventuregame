import java.util.Calendar;

public class GameClock {
	
	/*		GameClock
	 * 	Static class that keeps track of game time.
	 *  Methods return minutes and seconds remaining for
	 *  continue feature, returns a string of the remaining time
	 *  for the games map view, and is checked for for game over if the
	 *  player goes over the 20 minute  time limit (for current build). * 
	 * 
	 */
	
	
	// total end time in seconds
	private static int endTimeInSeconds;
	
	// game timer constant
	private static final int TIMELIMIT = 1;
	private static final int HOURCONV = 3600, MINCONV = 60;

	

	public static boolean isGameOver() {
		 int currentTimeInSeconds = getTimeInSeconds();
		 boolean gameOver = true;
		 System.out.println(currentTimeInSeconds);
		 System.out.println(endTimeInSeconds);
		 if (currentTimeInSeconds < endTimeInSeconds)
			 gameOver = false;
		 return gameOver;
	}
	
	

	
	public static String getTimer()	{
		String remainingTime = "";
		int minutesRemaining = getMinutesRemaining();
		int secondsRemaining = getSecondsRemaining();		

		String mRemaining = String.format("%02d", minutesRemaining);
		String sRemaining = String.format("%02d", secondsRemaining);
		remainingTime =  mRemaining + ":" + sRemaining;

		return remainingTime;
	}
	
	public static int getMinutesRemaining()	{
		int currentTimeInSeconds = getTimeInSeconds();
		int timeRemaining = endTimeInSeconds - currentTimeInSeconds;
		int minutesRemaining = timeRemaining / MINCONV;
		
		return minutesRemaining;
	}
	
	
	public static int getSecondsRemaining()	{
		int currentTimeInSeconds = getTimeInSeconds();
		int timeRemaining = endTimeInSeconds - currentTimeInSeconds;
		int secondsRemaining = timeRemaining % MINCONV;	
		
		return secondsRemaining;
	}
	
	public static int getHoursRemaining()	{
		int currentTimeInSeconds = getTimeInSeconds();
		int timeRemaining = endTimeInSeconds - currentTimeInSeconds;
		int secondsRemaining = timeRemaining / HOURCONV;	
		
		return secondsRemaining;
	}
	
	private static int getTimeInSeconds() {
		Calendar currentTime = Calendar.getInstance();
		int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
		int currentMinute = currentTime.get(Calendar.MINUTE);
		int currentSecond = currentTime.get(Calendar.SECOND);
		int currentTimeInSeconds = (currentHour * HOURCONV) + (currentMinute * MINCONV) + currentSecond;
		return currentTimeInSeconds;
	}
	
	public void setTimeRemaining(int minutes, int seconds) {
		endTimeInSeconds = (minutes * MINCONV) + seconds;	
	}
	
	public static void resetTime() {
		int currentTimeInSeconds = getTimeInSeconds();
		 endTimeInSeconds = currentTimeInSeconds + (TIMELIMIT * MINCONV);
	}
	
}
