import java.util.Scanner;

public class Cipher extends Puzzle{


	private String hint="The hint is 'EJTKU'. And the algorithm is X(alphabetic index)-2\r\n";
	
	public Cipher(int x, int y, String question, String answer, String h){
		super(x, y, question, answer);
		this.hint = h;
	}
	
	
	public Cipher(Cipher cipher) {
		super(cipher.getX(), cipher.getY(), cipher.getQuestion(), cipher.getAnswer());
		this.hint = cipher.getHint();
	}
	
	
	public void playCipher() {
		
		boolean isCorrect = false;
		System.out.println(super.getQuestion());
				
		Scanner myObj2 = new Scanner(System.in);
		System.out.println("Hint(yes/no)?:");
	    String hint1 = myObj2.nextLine();
	    if(hint1.equalsIgnoreCase("yes") || hint1.equalsIgnoreCase("y")) {
	    	System.out.println(hint);
	    	
	    }
	    
	    
		for(int i=0;i<3;i++) {
			Scanner myObj = new Scanner(System.in);
			System.out.println("What is the name?:");
		    String userName = myObj.nextLine();
		   
		    
		    
		    if (userName.equalsIgnoreCase(super.getAnswer()) ) {
		    	System.out.println("Correct.");
		    	isCorrect=true;
		    	break;
		    }
		    else {
		    	System.out.println("Incorrect Answer.");
				System.out.println("Want to try again(yes/no)?:");
			    String yesNo = myObj2.nextLine();
			    if (yesNo.equalsIgnoreCase("yes")) {
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
	
	
	public String getHint() {
		return hint;
	}
	
	
	public String getQuestion() {
		return super.getQuestion();
	}
	
	public String getAnswer() {
		return super.getAnswer();
	}
	
	public String getSprite() {
		return super.getSprite();
	}
	
	public boolean isSolved() {
		return super.isSolved();
	}
	
	public int getX()
	{
		return super.getX();
	}
	
	public int getY() {
		return super.getY();
	}

}
