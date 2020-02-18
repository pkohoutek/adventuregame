import java.util.Scanner;

public class Puzzle {
	
	/*		Puzzle Class
	 * 
	 * 		Players solve puzzles and by solving puzzles
	 * 		are able to unlock the level door to move on to the next level	 * 
	 */
	
	
	
	private boolean solved;
	private int xPos;
	private int yPos;
	private String question, answer;
	private String sSprite = "?";
	
	public Puzzle(Puzzle p) {
		this.answer = p.answer ;
		this.question = p.question;
		this.xPos = p.xPos;
		this.yPos = p.yPos;
		this.solved = p.solved;
	}
	public Puzzle(int _x,int _y,String q,String a) {
		this.xPos = _x;
		this.yPos = _y;
		this.question = q;
		this.answer = a;
		solved = false;
	}
	
	// helper method to check answer
	private boolean checkAns(String tempAns) {
		return tempAns.equalsIgnoreCase(answer);
	}
	
	// method to play the puzzle
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
	
	// getter to check if puzzle is solved
	public boolean isSolved() {
		return solved;
	}
	
	// getter for puzzles x position on map
	public int getX()
	{
		return xPos;
	}	
	
	// getter for puzzles y position on map
	public int getY() {
		return yPos;
	}
	
	// getter for puzzle question
	public String getQuestion() {
		return question;
	}
	
	// getter for puzzle answer
	public String getAnswer() {
		return answer;
	}
	
	// getter for string representation of the puzzle sprite
	public String getSprite() {
		return sSprite;
	}
	
	// setter to set the puzzle as solved
	public void setSolved() {
		solved = true;
	}
	

	
	
}
