import java.util.Scanner;

public class Cipher extends Puzzle{

	// String hint attribute for Cypher
	private String hint="The hint is 'EJTKU'. And the algorithm is X(alphabetic index)-2\r\n";
	
	public Cipher(int x, int y, String question, String answer, String h){
		super(x, y, question, answer);
		this.hint = h;
	}
	
	
	public Cipher(Cipher cipher) {
		super(cipher.getX(), cipher.getY(), cipher.getQuestion(), cipher.getAnswer());
		this.hint = cipher.getHint();
	}
	
	// method to play cipher puzzle
	public void playCipher() {
		
		boolean isCorrect = false;
		Scanner myObj = new Scanner(System.in);
		System.out.println(super.getQuestion());

		System.out.println("Hint(yes/no)?:");
	    String hint1 = myObj.nextLine();
	    if(hint1.equalsIgnoreCase("yes") || hint1.equalsIgnoreCase("y")) {
	    	System.out.println(hint);
	    	
	    }
	    
	    // give the player 3 tries to get answer before exiting cipher puzzle
		for(int i=0;i<3;i++) {

			System.out.println("What is the name?:");
		    String userName = myObj.nextLine();   
		    
		    // check if name is correct
		    if (userName.equalsIgnoreCase(super.getAnswer()) ) {
		    	System.out.println("Correct.");
		    	isCorrect=true;
		    	break;
		    }
		    // else prompt them to enter again
		    else {
		    	System.out.println("Incorrect Answer.");
				System.out.println("Want to try again(yes/no)?:");
			    String yesNo = myObj.nextLine();
			    if (hint1.equalsIgnoreCase("yes") || hint1.equalsIgnoreCase("y")) {
			    	System.out.println("");
			    }
			    else if (yesNo.equalsIgnoreCase("no")){
			    	break;
			    }
		    }
		}
		if (isCorrect) {
			super.setSolved();
		}
	}
	
	
	// getter for hint String
	public String getHint() {
		return hint;
	}
	
	
	// getter for Question String
	public String getQuestion() {
		return super.getQuestion();
	}
	
	// getter for Answer string
	public String getAnswer() {
		return super.getAnswer();
	}
	
	// getter for String sprite representation
	public String getSprite() {
		return super.getSprite();
	}
	
	// getter to check if the cipher has been solved
	public boolean isSolved() {
		return super.isSolved();
	}
	
	// getter for Cipher's X position
	public int getX()
	{
		return super.getX();
	}
	
	// getter for Cipher's Y position	
	public int getY() {
		return super.getY();
	}

}
