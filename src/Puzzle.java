import java.util.Scanner;

public class Puzzle {
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
	private boolean checkAns(String tempAns) {
		return tempAns.equalsIgnoreCase(answer);
	}
	public void doPuzzle() {
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
	
	public boolean isSolved() {
		return solved;
	}
	
	public int getX()
	{
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public String getSprite() {
		return sSprite;
	}
	
	public void setSolved() {
		solved = true;
	}
	

	
	
}
