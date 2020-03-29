// import Scanner library
import java.util.Scanner;

/**
 * Cipher class is a subclass of Puzzle
 * Includes hints
 * @author Azhar
 *
 */
public class Cipher extends Puzzle{

	// String hint attribute for Cypher
	private String hint="The hint is 'EJTKU'. And the algorithm is X(alphabetic index)-2\r\n";
	
	/**
	 * Cipher constructor
	 * @param x int of cipher's x position on map
	 * @param y int of cipher's y position on map
	 * @param question String of cipher question
	 * @param answer String of cipher answer
	 * @param h String of cipher hint
	 */
	public Cipher(int x, int y, String question, String answer, String h){
		super(x, y, question, answer);
		this.hint = h;
	}
	
	/**
	 * Cipher copy constuctor
	 * @param cipher to copy
	 */
	public Cipher(Cipher cipher) {
		super(cipher.getX(), cipher.getY(), cipher.getQuestion(), cipher.getAnswer());
		this.hint = cipher.getHint();
	}
	
	/**
	 * 	method to play cipher puzzle
	 */
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
	
	
	/**
	 * getter for hint String
	 * @return String of cipher hint
	 */
	public String getHint() {
		return hint;
	}
	
	
	/**
	 *  getter for Question String
	 *  @return String of cipher question
	 */
	public String getQuestion() {
		return super.getQuestion();
	}
	
	/**
	 * getter for Answer string
	 * @return String of cipher answer
	 */
	public String getAnswer() {
		return super.getAnswer();
	}
	
	// getter for String sprite representation
	public String getSprite() {
		return super.getSprite();
	}
	
	/**
	 * getter to check if the cipher has been solved
	 * @return boolean true if solved
	 */
	public boolean isSolved() {
		return super.isSolved();
	}
	
	/**
	 * getter for Cipher's X position
	 * @return int of cipher's x position
	 */
	public int getX()
	{
		return super.getX();
	}
	
	/**
	 * getter for Cipher's Y position	
	 * @return int of cipher's y position
	 */
	public int getY() {
		return super.getY();
	}

}
