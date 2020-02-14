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
	private int startX = 0;
	private int startY = 0;

	private ArrayList<Prop> props;
	private ArrayList<Wall> walls; // walls for level design
	private ArrayList<Prop> puzzles;
	private ArrayList<Prop> triggers; 
	private ArrayList<String> storyText;
	private ArrayList<String> descriptions;
	private ArrayList<String> triggerText;
	private Map map;
	

	
	
	
	public LevelGenerator(int lNumber){
		levelNumber = lNumber;
		walls = new ArrayList<Wall>();
		props = new ArrayList<Prop>();
		triggers = new ArrayList<Prop>();
		puzzles = new ArrayList<Prop>();
		storyText = new ArrayList<String>();
		descriptions = genDescription();
		triggerText = genTriggerText();
		
		generate();
		genPlayerStart();
		putObjectsInMap();

	}
	
	private void putObjectsInMap()
	{
		
		map = new Map(levelX, levelY);
		
		for (int num = 0; num < walls.size(); num++) 
		{
			Wall wall = new Wall(walls.get(num));
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
			String line = inputStream.nextLine();
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
					else if (line.equals("\n"))
					{
						description = "" + description + "\n";	
					}
					else
					{
						description = "" + description + line + "\n";
					}
				}
			}
		}
		inputStream.close();
		return propDesc;		
	}
	
	private ArrayList<String> genTriggerText()
	{
		ArrayList<String> trTexts = new ArrayList<String>();
		boolean endTrText = false;
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".prop";
		try
		{
			File file = new File(filename);
			inputStream = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error generating trigger. Exiting game.");
			System.out.println(e);
			System.exit(0);
		}

		while (inputStream.hasNextLine() && !endTrText)
		{
			String line = inputStream.nextLine();
			String trText = "";

			if (line.equals("[TRIGGER]"))
			{
				while(!endTrText)
				{
					line = inputStream.nextLine();
					if (line.equals("[/TRIGGER]"))
					{
						endTrText = true;
					}
					else if  (line.equals("[/trigger]"))
					{
						trTexts.add(trText);
						trText = "";
					}
					else if (line.equals("\n"))
					{
						trText = "" + trText + "\n";	
					}
					else
					{
						trText = "" + trText + line + "\n";
					}
				}
			}
		}
		inputStream.close();
		return trTexts;		
				
	}
	
	private void genPlayerStart()
	{
		boolean endStart = false;
		int count = 0;
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".prop";
		try
		{
			File file = new File(filename);
			inputStream = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening level file. Exiting game.");
			System.out.println(e);
			System.exit(0);
		}

		while (inputStream.hasNextLine() && !endStart)
		{
			String line = inputStream.nextLine();
			line.trim();

			if (line.equals("[START]") && !endStart)
			{
				while(!endStart)
				{
					line = inputStream.nextLine();
					if (line.equals("[/START]"))
					{
						endStart = true;
					}
					else if (count == 0)
					{
						try {
							startX = Integer.parseInt(line);
							count++;
						}
						catch(NumberFormatException e)
						{
							System.out.println("Error generating player start. Exiting game.");
							System.out.println(e);
							System.exit(1);
						}
					}	
					else if (count == 1){
						try {
							startY = Integer.parseInt(line);
							count++;
						}
						catch(NumberFormatException e)
						{
							System.out.println("Error generating player start. Exiting game.");
							System.out.println(e);
							System.exit(1);
						}
					}
				}
			}
		}
	}
	
	
	public void generate() {
		walls = new ArrayList<Wall>();
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".map";
		ArrayList<String> sMap = new ArrayList<String>();
		ArrayList<String> tMap = new ArrayList<String>();
		int propCount = 0, triggerCount = 0;

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
					Wall wall = new Wall(x, yIndex);
					walls.add(wall);
				}
				else if (c == '$')
				{
					String propDes = descriptions.get(propCount);
					propCount++;
					Prop prop = new Prop(propDes, x, yIndex, isTrigger);
					props.add(prop);
				}
				else if (c == 'x')
				{
					String trText = triggerText.get(triggerCount);
					triggerCount++;
					Prop trigger = new Prop(trText, x, yIndex, true);
					triggers.add(trigger);
					
					
					
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
	
	public ArrayList<Prop> getTriggers()
	{
		ArrayList<Prop> tTriggers = new ArrayList<Prop>();
		for (int num = 0; num < triggers.size(); num++)
		{
			tTriggers.add(new Prop(triggers.get(num)));
		}
		return tTriggers;
	}
	
	
	public int getPlayerStartX()
	{
		return startX;
	}
	
	
	public int getPlayerStartY()
	{
		return startY;
	}
	
}
