import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class LevelGenerator {
	
	/*
	 * 	Test class to use a text file to generate a map
	 * 	WIP
	 * 
	 */
	
	private int levelNumber = 0;
	private int levelX = 0;
	private int levelY = 0;

	private ArrayList<Prop> props = new ArrayList<Prop>();
	private ArrayList<Prop> walls; // walls for level design
	private ArrayList<Prop> puzzles = new ArrayList<Prop>();
	private ArrayList<Prop> triggers = new ArrayList<Prop>(); 
	private ArrayList<String> storyText = new ArrayList<String>();
	private ArrayList<String> description = new ArrayList<String>();
	

	
	public LevelGenerator(int lNumber){
		levelNumber = lNumber;
		walls = new ArrayList<Prop>();
		generateWallFromFile();
		Map testMap = new Map(levelX, levelY);
		for (int num = 0; num < walls.size(); num++) 
		{
			Prop wall = new Prop(walls.get(num));
			testMap.addProp(wall);
		}
		
		testMap.printMap(2, 2);

	}
	
	public void generateWallFromFile() {
		walls = new ArrayList<Prop>();
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".lvl";
		int y = 0;
		try
		{
			inputStream = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error generating walls. Exiting game.");
			System.exit(0);
		}
		while (inputStream.hasNextLine())
		{
			String line = inputStream.nextLine();
			line.trim();
			if (y == 0)
			{
				levelX = line.length();
			}
			for (int x = 0; x < line.length(); x++)
			{
				char c = line.charAt(x);
				if (c == '#')
				{
					Prop wall = new Prop(x, y);
					walls.add(wall);
				}
				
			}
			y++;
		}
		levelY = y;
	}
}

