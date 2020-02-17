import java.util.Scanner;

public class Puzzle {
	private boolean solved;
	private int xPos;
	private int yPos;
	private String question,answer;
	
	public Puzzle(Puzzle p) {
		this.answer =p.answer ;
		this.question=p.question;
		this.xPos=p.xPos;
		this.yPos=p.yPos;
		this.solved=p.solved;
	}
	public Puzzle(int _x,int _y,String q,String a) {
		this.xPos = _x;
		this.yPos=_y;
		this.question=q;
		this.answer=a;
		solved = false;
	}
	private boolean checkAns(String tempAns) {
		return tempAns.equals(answer);
	}
	public void doPuzzle() {
		System.out.println(question);
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your answer or press 'Q' to quit");
		String ans = input.nextLine();
		if (ans.equalsIgnoreCase("Q")){
			input.close();
			return;
		}
		while(!checkAns(ans)) {
			System.out.println("Your answer was wrong! Please try again.");
			System.out.println("Enter your answer or press 'Q' to quit");
			ans = input.nextLine();
			if (ans.equalsIgnoreCase("Q")){
				input.close();
				return;
			}
		}
		solved = true;
		input.close();
		
	}
	public boolean isSolved() {
		return solved;
	}
}
