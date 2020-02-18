



import java.util.Scanner;
public class solution1 {
	private static boolean isCorrect= false;
	private static String solution1= "Chris";
	private static String clue="The hint is 'EJTKU'. And the algorithm is X(alphabetic index)-2\r\n";
	public static void main(String[] args) {
		boolean result = cipher();
	}
	
	public static boolean cipher() {
		System.out.println("You open a drawer of Scarlett’s desk and find some financial documents….\r\n"+
				"It looks right before Scarlett died, she changed her will so her entire fortune\r\n"
				+ "would go to someone she had just met 2 weeks prior – her new romantic partner \r\n"+
				"that she just began seeing. He was probably at the party too, but the only way\r\n"+
				"to find out is to translate the cipher\r\nThat shows his name on this paper\r\n");
				
				Scanner myObj2 = new Scanner(System.in);
				System.out.println("Hint(yes/no)?:");
			    String hint1 = myObj2.nextLine();
			    if(hint1.equalsIgnoreCase("yes")) {
			    	System.out.println(clue);
			    	
			    }
			    
			    
				for(int i=0;i<3;i++) {
				Scanner myObj = new Scanner(System.in);
				System.out.println("What is the name?:");
			    String userName = myObj.nextLine();
			   
			    
			    
			    if (userName.equalsIgnoreCase(solution1) ) {
			    	System.out.println("Correct.");
			    	isCorrect=true;
			    	break;
			    }else {
			    	System.out.println("Incorrect Answer.");
			    	Scanner myObj3 = new Scanner(System.in);
					System.out.println("Want to try again(yes/no)?:");
				    String yesNo = myObj2.nextLine();
				    if (yesNo.equalsIgnoreCase("yes")) {
				    	System.out.println("");
				    }else if (yesNo.equalsIgnoreCase("no")){
				    	break;
				    }
			    }
			}
	return isCorrect;
	}
}