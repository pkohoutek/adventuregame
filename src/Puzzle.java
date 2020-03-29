import java.util.Scanner;

/**
 * Puzzle Class
 * Players solve puzzles and by solving puzzles
 * 	are able to unlock the level door to move on to the next level	 * 
 */
public class Puzzle {
	
	private boolean solved;
	private int xPos;
	private int yPos;
	private String question, answer;
	private String sSprite = "?";
	
	/**
	 * Copy constructor for Puzzle objects
	 * @param p Puzzle to copy
	 */
	public Puzzle(Puzzle p) {
		this.answer = p.answer ;
		this.question = p.question;
		this.xPos = p.xPos;
		this.yPos = p.yPos;
		this.solved = p.solved;
	}
	
	/**
	 * Puzzle constructor
	 * @param _x int of puzzle's x position on level map
	 * @param _y int of puzzle's y position on level map
	 * @param q String of puzzle's question
	 * @param a String of puzzle's answer
	 */
	public Puzzle(int _x,int _y,String q,String a) {
		this.xPos = _x;
		this.yPos = _y;
		this.question = q;
		this.answer = a;
		solved = false;
	}
	
	/**
	 * helper method to check answer
	 * @param tempAns String of user's inputed answer
	 * @return boolean true if answer is correct
	 */
	private boolean checkAns(String tempAns) {
		return tempAns.equalsIgnoreCase(answer);
	}
	
	/**
	 * method to play the puzzle
	 */
	public void playPuzzle() {
		System.out.println(question);
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your answer or press 'Q' to quit");
		String ans = input.nextLine();
		ans = "" + ans.trim();
		if (ans.equalsIgnoreCase("Q")){
			return;
		}
		while(!checkAns(ans)) {
			System.out.println("Your answer was wrong! Please try again.");
			System.out.println("Enter your answer or press 'Q' to quit");
			ans = input.nextLine();
			if (ans.equalsIgnoreCase("Q")){
				return;
			}
		}
		System.out.println("Correct!");
		setSolved();		
	}
	
	/**
	 * getter to check if puzzle is solved
	 * @return boolean true if the puzzle has been solved
	 */
	public boolean isSolved() {
		return solved;
	}
	
	/**
	 * getter for puzzles x position on map
	 * @return int of puzzle's x position on level map
	 */
	public int getX()
	{
		return xPos;
	}	
	
	/**
	 * getter for puzzles y position on map
	 * @return int of puzzle's y position on level map
	 */
	public int getY() {
		return yPos;
	}
	
	/**
	 * getter for puzzle question
	 * @return String of puzzle's question text
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * getter for puzzle answer
	 * @return String of puzzle's answer
	 */
	public String getAnswer() {
		return answer;
	}
	
	/**
	 * getter for string representation of the puzzle sprite
	 * @return String of puzzle's string representation on game map
	 */
	public String getSprite() {
		return sSprite;
	}
	
	/**
	 * setter to set the puzzle as solved
	 */
	public void setSolved() {
		solved = true;
	}	
	
}
