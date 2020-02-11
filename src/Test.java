import java.util.Scanner;


public class Test {

	private static boolean finishedTurn = false, gameOver = false;

	

	public static void main(String[] args) {
		
		Player player = new Player(0, 0);
		Scanner keyboard = new Scanner(System.in);
		// Map map = new Map(5, 5);
		
		Level testLevel = new Level(1, 1, 1, 0, 0, "Escape room story goes here", "Level 0 is very mysterious... oOoooOOoh", 5, 6);

		
	
		// this is the player controller, can be loaded by the scene
		String sOption;
		int iOption, iMove;		
		
		while (!gameOver) 
		{
			finishedTurn = false;
			boolean bonk = false, invalidEntry = false;
			
			while (!finishedTurn)
			{
				//map.printMap(player.getX(), player.getY());
				testLevel.displayLevel(player.getX(), player.getY());
				if (invalidEntry)
				{
					System.out.println("Invalid entry!");
					invalidEntry = false;
				}
				System.out.println("Please choose an option: ");
				System.out.println("1 - Move\t 2 - Inspect \t 0 - Exit");
				while (!keyboard.hasNextInt())
				{
					//map.printMap(player.getX(), player.getY());
//					testLevel.displayLevel(player.getX(), player.getY());
//					System.out.println("Invalid entry!");
//					System.out.println("1 - Move\t 2 - Inspect \t 0 - Exit");
					sOption = keyboard.next();

				}
				iOption = keyboard.nextInt();
				if (iOption == 1)
				{
					boolean back = false;
						while(!back)
						{
							if (bonk)
							{
								//map.printMap(player, bonk);
								testLevel.displayLevel(player.getX(), player.getY(), bonk);
								bonk = false;
							}
							else if (invalidEntry)
							{
								testLevel.displayLevel(player.getX(), player.getY());
								System.out.println("Invalid entry!");
								invalidEntry = false;
							}
							else
							{
								//map.printMap(player.getX(), player.getY());
								testLevel.displayLevel(player.getX(), player.getY());
								System.out.print("\n");
							}	
							System.out.println("Move Up - 1\tDown - 2\tMove Left - 3");
							System.out.println("Right - 4\tBack a Menu - 0");
							while (!keyboard.hasNextInt())
							{
								//map.printMap(player.getX(), player.getY());
								testLevel.displayLevel(player.getX(), player.getY());
								System.out.println("Invalid entry!");
								System.out.println("Move Up - 1\tDown - 2\tMove Left - 3");
								System.out.println("Right - 4\tBack a Menu - 0");
								sOption = keyboard.next();
							}
							iMove = keyboard.nextInt();
							if (iMove < 0 || iMove > 4)
							{
								invalidEntry = true;
							}
							else if(iMove == 0)
							{
								back= true;
							}
							else
							{
								if (testLevel.checkMove(iMove, player.getX(), player.getY()))
								{
									player.move(iMove);
								}
								else {
									bonk = true;
								}
							}
						}
					
					finishedTurn = true;
				}
				else if (iOption == 2)
				{
					finishedTurn = true;
				}
				else if (iOption == 0)
				{
					finishedTurn = true;
					gameOver = true;
				}
				else
				{
					invalidEntry = true;
					System.out.println("Invalid entry!");
				}
			}

		
		}

	} 
}
