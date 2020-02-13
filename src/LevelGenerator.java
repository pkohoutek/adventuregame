import java.io.File;
import java.io.*;
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

	private ArrayList<Prop> props;
	private ArrayList<Prop> walls; // walls for level design
	private ArrayList<Prop> puzzles;
	private ArrayList<Prop> triggers; 
	private ArrayList<String> storyText;
	private ArrayList<String> descriptions;
	private Map map;
	

	
	
	
	public LevelGenerator(int lNumber){
		levelNumber = lNumber;
		walls = new ArrayList<Prop>();
		props = new ArrayList<Prop>();
		triggers = new ArrayList<Prop>();
		puzzles = new ArrayList<Prop>();
		storyText = new ArrayList<String>();
		descriptions = genDescription();
		
		generate();
		putObjectsInMap();

	}
	
	private void putObjectsInMap()
	{
		
		map = new Map(levelX, levelY);
		
		for (int num = 0; num < walls.size(); num++) 
		{
			Prop wall = new Prop(walls.get(num));
			map.addProp(wall);
		}
		for (int num = 0; num < props.size(); num++)
		{
			Prop prop = new Prop(props.get(num));
			map.addProp(prop);
		}
	}
	
	public Map getMap()
	{
		Map tMap = new Map(map);
		return tMap;
	}
	

	
	private ArrayList<String> genDescription()
	{
		ArrayList<String> propDesc = new ArrayList<String>();
		boolean endDescriptions = false;
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".prop";
		try
		{
			File file = new File(filename);
			inputStream = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error generating prop. Exiting game.");
			System.out.println(e);
			System.exit(0);
		}

		while (inputStream.hasNextLine() && !endDescriptions)
		{
			System.out.println(endDescriptions);
			String line = inputStream.nextLine();
			line.trim();
			String description = "";

			if (line.equals("[PROPDES]") && !endDescriptions)
			{
				while(!endDescriptions)
				{
					line = inputStream.nextLine();
					if (line.equals("[/PROPDES]"))
					{
						endDescriptions = true;
					}
					else if  (line.equals("[/des]"))
					{
						propDesc.add(description);
						description = "";
					}
					else
					{
						description = "" + description + line;
					}
				}
			}
		}
		return propDesc;
		
	}
	
	
	public void generate() {
		walls = new ArrayList<Prop>();
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".map";
		ArrayList<String> sMap = new ArrayList<String>();
		ArrayList<String> tMap = new ArrayList<String>();
		int propCount = 0;

		try
		{
			File file = new File(filename);
			inputStream = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error generating walls. Exiting game.");
			System.out.println(e);
			System.exit(1);
		}		
		while (inputStream.hasNextLine())
		{
			String line = inputStream.nextLine();
			line.trim();
			tMap.add(line);

		}		
		for (int index = tMap.size() - 1; index >= 0; index--)
		{
			sMap.add(tMap.get(index));
		}
		for (int yIndex = 0; yIndex < sMap.size(); yIndex++)
		{
			boolean isTrigger = false;

			for (int x = 0; x < sMap.get(yIndex).length(); x++)
			{
				char c = sMap.get(yIndex).charAt(x);
				if (c == '#')
				{
					Prop wall = new Prop(x, yIndex);
					walls.add(wall);
				}
				else if (c == '$')
				{
					String propDes = descriptions.get(propCount);
					propCount++;
					Prop prop = new Prop(propDes, x, yIndex, isTrigger);
					props.add(prop);
				}
				
			}
		} 
		inputStream.close();
		levelY = sMap.size();
		levelX = sMap.get(0).length();
	}
	
	public ArrayList<Prop> getProps()
	{
		ArrayList<Prop> tProps = new ArrayList<Prop>();
		for (int num = 0; num < props.size(); num++)
		{
			tProps.add(new Prop(props.get(num)));
		}
		return tProps;
	}
	
}
