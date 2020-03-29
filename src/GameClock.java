// import Calendar library
import java.util.Calendar;

/** 
 * GameClock
 * class that keeps track of game time.
 * Methods return minutes and seconds remaining for
 * continue feature, returns a string of the remaining time
 * for the games map view, and is checked for for game over if the
 * player goes over the 20 minute time limit (for current build). 
 */
public class GameClock {
	
	
	// total end time in seconds
	private int endTimeInSeconds;
	
	// game timer constant
	private final int TIMELIMIT = 20;
	private final int HOURCONV = 3600, MINCONV = 60;

	/**
	 * GameClock default constructor
	 */
	public GameClock() {
		resetTime();
	}
	
	/**
	 * GameClock copy constructor
	 * @param clock to copy
	 */
	public GameClock(GameClock clock) {
		this.endTimeInSeconds = clock.endTimeInSeconds;
	}
	
	/**
	 * 	getter method that checks if the player has gone over the game's time limit
	 * @return boolean true if time is up and game is over
	 */
	public boolean isGameOver() {
		 int currentTimeInSeconds = getTimeInSeconds();
		 boolean gameOver = true;
		 if (currentTimeInSeconds < endTimeInSeconds)
			 gameOver = false;
		 return gameOver;
	}	
	

	/**
	 * getter method returns a string time in "00:00" format for the game's map view
	 * @return String of time remaining
	 */
	public String getTimer() {
		String remainingTime = "";
		int minutesRemaining = getMinutesRemaining();
		int secondsRemaining = getSecondsRemaining();		

		String mRemaining = String.format("%02d", minutesRemaining);
		String sRemaining = String.format("%02d", secondsRemaining);
		remainingTime =  mRemaining + ":" + sRemaining;

		return remainingTime;
	}
	
	/**
	 * 	getter returns minutes remaining for saving game and works as helper method for getTimer()
	 * @return int minutes remaining
	 */
	public int getMinutesRemaining() {
		int currentTimeInSeconds = getTimeInSeconds();
		int timeRemaining = endTimeInSeconds - currentTimeInSeconds;
		int minutesRemaining = timeRemaining / MINCONV;
		
		return minutesRemaining;
	}
	
	/**
	 * getter returns seconds remaining for saving game and works as a helper
	 * method for getTimer()
	 * @return int of seconds remaining
	 */
	public int getSecondsRemaining() {
		int currentTimeInSeconds = getTimeInSeconds();
		int timeRemaining = endTimeInSeconds - currentTimeInSeconds;
		int secondsRemaining = timeRemaining % MINCONV;	
		
		return secondsRemaining;
	}
	
	/**
	 * 	getter returns hours remaining if needed in the future
	 * @return int of hours remaining
	 */
	public int getHoursRemaining() {
		int currentTimeInSeconds = getTimeInSeconds();
		int timeRemaining = endTimeInSeconds - currentTimeInSeconds;
		int hoursRemaining = timeRemaining / HOURCONV;	
		
		return hoursRemaining;
	}
	
	// private helper method to get time in seconds for calculations
	// in other methods
	private int getTimeInSeconds() {
		Calendar currentTime = Calendar.getInstance();
		int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
		int currentMinute = currentTime.get(Calendar.MINUTE);
		int currentSecond = currentTime.get(Calendar.SECOND);
		int currentTimeInSeconds = (currentHour * HOURCONV) + (currentMinute * MINCONV) + currentSecond;
		return currentTimeInSeconds;
	}
	
	/**
	 * setter method to set time remaining when loading from save game
	 * @param minutes int of minutes remaining from save file
	 * @param seconds int of seconds remaining from save file
	 */
	public void setTimeRemaining(int minutes, int seconds) {
		endTimeInSeconds = (minutes * MINCONV) + seconds + getTimeInSeconds();	
	}
	
	/**
	 * 	method resets the game clock when starting a new game or instantiating GameClock object
	 */
	public void resetTime() {
		int currentTimeInSeconds = getTimeInSeconds();
		endTimeInSeconds = currentTimeInSeconds + (TIMELIMIT * MINCONV);
	}
	
}
