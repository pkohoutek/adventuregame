import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class LevelGenerator {
	
	/*
	 * 	Test class to use a text file to generate a map
	 * 	WIP
	 * 	Currently able to build map with walls, interactive props,
	 * 	and triggers.
	 * 	
	 * 	Plan to add functionality for whatever puzzles and other
	 * 	features are implemented in the future
	 */
	
	
	// config parameters
	private int levelNumber = 0;
	private int levelX = 0;				// level x/y length for map generation
	private int levelY = 0;
	
	private int startX = 0;				// player x/y start position in level
	private int startY = 0;

	// Arrays for the props, walls, puzzles, story texts, triggers, etc
	private ArrayList<Prop> props;
	private ArrayList<Wall> walls; // walls for level design
	private ArrayList<Puzzle> puzzles;
	private ArrayList<Trigger> triggers; 
	private ArrayList<Cipher> ciphers;
	private ArrayList<String> storyText;
	private ArrayList<String> descriptions;
	private ArrayList<String> triggerText;
	private ArrayList<String> pQuestions;
	private ArrayList<String> pAnswers;
	private ArrayList<String> ciphQuestions;
	private ArrayList<String> ciphAnswers;
	private ArrayList<String> ciphHints;
	private String introText, exitText;
	private Door door;
	private Map map;						// generates level map
	

	
	
	// Level constructor takes level number and generates level from .map and .cfg files
	// this class could be split up to helper classes for each object generated
	public LevelGenerator(int lNumber){
		levelNumber = lNumber;
		walls = new ArrayList<Wall>();
		props = new ArrayList<Prop>();
		triggers = new ArrayList<Trigger>();
		puzzles = new ArrayList<Puzzle>();
		ciphers = new ArrayList<Cipher>();
		storyText = new ArrayList<String>();
		descriptions = genPropDescriptions();
		triggerText = genTriggerText();
		pQuestions = genPuzzleQuestions();
		pAnswers = genPuzzleAnswers();
		ciphQuestions = genCipherQuestions();
		ciphHints = genCipherHints();
		ciphAnswers = genCipherAnswers();
		genLevelText();
		generate();
		genPlayerStart();
		putObjectsInMap();

	}
	
	// puts walls and other visible objects (not triggers) in the map
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
		
		for (int num = 0; num < puzzles.size(); num++)
		{
			Puzzle puzzle = new Puzzle(puzzles.get(num));
			map.addPuzzle(puzzle);
		}
		
		for (int num = 0; num < ciphers.size(); num++)
		{
			Cipher cipher = new Cipher(ciphers.get(num));
			map.addPuzzle(cipher);
		}
		Door tDoor = new Door(door);
		map.addDoor(tDoor);
	}
	
	
	// returns copy of the level map 
	public Map getMap()
	{
		Map tMap = new Map(map);
		return tMap;
	}
	

	// method creates a string ArrayList of the descriptions of props
	private ArrayList<String> genPropDescriptions()
	{
		ArrayList<String> propDesc = new ArrayList<String>();
		boolean endDescriptions = false;
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".cfg";
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
					else if  (line.equals("<pdes>"))
					{
						description = "";
					}
					else if  (line.equals("</pdes>"))
					{
						propDesc.add(description);
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

	// method generates trigger text for triggers in the level 
	private ArrayList<String> genTriggerText()
	{
		ArrayList<String> trTexts = new ArrayList<String>();
		boolean endTrText = false;
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".cfg";
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
					else if  (line.equals("<trigger>"))
					{
						trText = "";
					}
					else if  (line.equals("</trigger>"))
					{
						trTexts.add(trText);
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
	
	// method generates trigger text for triggers in the level 
	private ArrayList<String> genPuzzleQuestions()
	{
		ArrayList<String> puzQuestions = new ArrayList<String>();
		boolean endPuzText = false;
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".cfg";
		try
		{
			File file = new File(filename);
			inputStream = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error generating puzzle questions. Exiting game.");
			System.out.println(e);
			System.exit(0);
		}

		while (inputStream.hasNextLine() && !endPuzText)
		{
			String line = inputStream.nextLine();
			String puzQText = "";
			String puzAText = "";

			if (line.equals("[PUZZLE]"))
			{
				while(!endPuzText)
				{
					line = inputStream.nextLine();
					line.trim();
					if (line.equals("<PQUESTIONS>"))
					{
						boolean endQuestions = false;
						while (!endQuestions)
						{
							line = inputStream.nextLine();
							line.trim();
							if (line.equals("</PQUESTIONS>"))
							{
								endQuestions = true;
							}
							else if (line.equals("<pquestion>"))
							{
								puzQText = "";
							}
							else if (line.equals("</pquestion>"))
							{
								puzQuestions.add(puzQText);
							}
							else if (line.equals("\n"))
							{
								puzQText = "" + puzQText + "\n";	
							}
							else
							{
								puzQText = "" + puzQText + line + "\n";
							}
						}
					}
					if (line.equals("[/PUZZLE]"))
					{
						endPuzText = true;
					}	

				}
			}
		}
		inputStream.close();
		return puzQuestions;						
	}
	
	
	// method generates trigger text for triggers in the level 
	private ArrayList<String> genPuzzleAnswers()
	{

		ArrayList<String> puzAnswers = new ArrayList<String>();
		boolean endPuzText = false;
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".cfg";
		try
		{
			File file = new File(filename);
			inputStream = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error generating puzzle answers. Exiting game.");
			System.out.println(e);
			System.exit(0);
		}

		while (inputStream.hasNextLine() && !endPuzText)
		{
			String line = inputStream.nextLine();
			String puzAText = "";

			if (line.equals("[PUZZLE]"))
			{
				while(!endPuzText)
				{
					line = inputStream.nextLine();
					line.trim();
					if (line.equals("<PANSWERS>"))
					{							

						boolean endAnswers = false;
						while (!endAnswers)
						{
							line = inputStream.nextLine();
							line.trim();
							if (line.equals("</PANSWERS>"))
							{
								endAnswers = true;
							}
							else if  (line.equals("<panswer>"))
							{
								puzAText = "";
							}
							else if  (line.equals("</panswer>"))
							{
								puzAnswers.add(puzAText);
							}
							else if (line.equals("\n"))
							{
								puzAText = "" + puzAText + "\n";	
							}
							else
							{
								puzAText = "" + puzAText + line;
							}
						}
					}					
					if (line.equals("[/PUZZLE]"))
					{
						endPuzText = true;
					}
				}
			}
		}
		inputStream.close();
		return puzAnswers;						
	}
	
	
	// method generates trigger text for triggers in the level 
	private ArrayList<String> genCipherQuestions()
	{

		ArrayList<String> cipherQuestions = new ArrayList<String>();
		boolean endCiphText = false;
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".cfg";
		try
		{
			File file = new File(filename);
			inputStream = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error generating cipher questions. Exiting game.");
			System.out.println(e);
			System.exit(0);
		}

		while (inputStream.hasNextLine() && !endCiphText)
		{
			String line = inputStream.nextLine();
			String ciphQText = "";

			if (line.equals("[CIPHER]"))
			{
				while(!endCiphText)
				{
					line = inputStream.nextLine();
					line.trim();
					if (line.equals("<CQUESTIONS>"))
					{							

						boolean endQuestions = false;
						while (!endQuestions)
						{
							line = inputStream.nextLine();
							line.trim();
							if (line.equals("</CQUESTIONS>"))
							{
								endQuestions = true;
							}
							else if  (line.equals("<cquestion>"))
							{
								ciphQText = "";
							}
							else if  (line.equals("</cquestion>"))
							{
								cipherQuestions.add(ciphQText);
							}
							else if (line.equals("\n"))
							{
								ciphQText = "" + ciphQText + "\n";	
							}
							else
							{
								ciphQText = "" + ciphQText + line + "\n";
							}
						}
					}					
					if (line.equals("[/CIPHER]"))
					{
						endCiphText = true;
					}
				}
			}
		}
		inputStream.close();
		return cipherQuestions;						
	}
	
	// method generates trigger text for triggers in the level 
	private ArrayList<String> genCipherHints()
	{

		ArrayList<String> cipherHints = new ArrayList<String>();
		boolean endCiphText = false;
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".cfg";
		try
		{
			File file = new File(filename);
			inputStream = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error generating cipher hints. Exiting game.");
			System.out.println(e);
			System.exit(0);
		}

		while (inputStream.hasNextLine() && !endCiphText)
		{
			String line = inputStream.nextLine();
			String ciphHText = "";

			if (line.equals("[CIPHER]"))
			{
				while(!endCiphText)
				{
					line = inputStream.nextLine();
					line.trim();
					if (line.equals("<CHINTS>"))
					{							

						boolean endQuestions = false;
						while (!endQuestions)
						{
							line = inputStream.nextLine();
							line.trim();
							if (line.equals("</CHINTS>"))
							{
								endQuestions = true;
							}
							else if  (line.equals("<chint>"))
							{
								ciphHText = "";
							}
							else if  (line.equals("</chint>"))
							{
								cipherHints.add(ciphHText);
							}
							else if (line.equals("\n"))
							{
								ciphHText = "" + ciphHText + "\n";	
							}
							else
							{
								ciphHText = "" + ciphHText + line + "\n";
							}
						}
					}					
					if (line.equals("[/CIPHER]"))
					{
						endCiphText = true;
					}
				}
			}
		}
		inputStream.close();
		return cipherHints;						
	}
	
	
	// method generates trigger text for triggers in the level 
	private ArrayList<String> genCipherAnswers()
	{

		ArrayList<String> cipherAnswers = new ArrayList<String>();
		boolean endCiphText = false;
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".cfg";
		try
		{
			File file = new File(filename);
			inputStream = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error generating cipher answers. Exiting game.");
			System.out.println(e);
			System.exit(0);
		}

		while (inputStream.hasNextLine() && !endCiphText)
		{
			String line = inputStream.nextLine();
			String ciphAText = "";

			if (line.equals("[CIPHER]"))
			{
				while(!endCiphText)
				{
					line = inputStream.nextLine();
					line.trim();
					if (line.equals("<CANSWERS>"))
					{							

						boolean endQuestions = false;
						while (!endQuestions)
						{
							line = inputStream.nextLine();
							line.trim();
							if (line.equals("</CANSWERS>"))
							{
								endQuestions = true;
							}
							else if  (line.equals("<canswer>"))
							{
								ciphAText = "";
							}
							else if  (line.equals("</canswer>"))
							{
								cipherAnswers.add(ciphAText);
							}
							else if (line.equals("\n"))
							{
								ciphAText = "" + ciphAText + "\n";	
							}
							else
							{
								ciphAText = "" + ciphAText + line;   // no new line to avoid String equals not working
							}
						}
					}					
					if (line.equals("[/CIPHER]"))
					{
						endCiphText = true;
					}
				}
			}
		}
		inputStream.close();
		return cipherAnswers;						
	}
	
	// method generates player X, Y start position from the level config file
	private void genPlayerStart()
	{
		boolean endStart = false;
		int count = 0;
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".cfg";
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
	
	// method generates level intro and exit text from .cfg file
	private void genLevelText()
	{
		boolean endLevelText = false;
		String tIntroText = "";
		String tExitText = "";
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".cfg";
		try
		{
			File file = new File(filename);
			inputStream = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error generating puzzle questions. Exiting game.");
			System.out.println(e);
			System.exit(0);
		}

		while (inputStream.hasNextLine() && !endLevelText)
		{
			String line = inputStream.nextLine();
			line.trim();


			if (line.equals("[LEVELTEXT]"))
			{
				while(!endLevelText)
				{
					line = inputStream.nextLine();
					line.trim();
					if (line.equals("<STARTTEXT>"))
					{
						boolean endIntro = false;
						while (!endIntro)
						{
							line = inputStream.nextLine();
							line.trim();
							if (line.equals("</STARTTEXT>"))
							{
								endIntro = true;
							}
							else if (line.equals("\n"))
							{
								tIntroText = "" + tIntroText + "\n";	
							}
							else
							{
								tIntroText = "" + tIntroText + line + "\n";
							}
						}
					}
					if (line.equals("<ENDTEXT>"))
					{
						boolean endEnding = false;
						while (!endEnding)
						{
							line = inputStream.nextLine();
							line.trim();
							if (line.equals("</ENDTEXT>"))
							{
								endEnding = true;
							}
							else if (line.equals("\n"))
							{
								tExitText = "" + tExitText + "\n";	
							}
							else
							{
								tExitText = "" + tExitText + line + "\n";
							}
						}
					}
					if (line.equals("[/LEVELTEXT]"))
					{
						endLevelText = true;
					}	

				}
			}
		}
		inputStream.close();
		introText = tIntroText;
		exitText = tExitText;
	}
	
	// method generates walls, props, and triggers
	// will be expanded for future objects
	private void generate() {
		walls = new ArrayList<Wall>();
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".map";
		ArrayList<String> sMap = new ArrayList<String>();
		ArrayList<String> tMap = new ArrayList<String>();
		int propCount = 0, triggerCount = 0, puzzleCount = 0, cipherCount = 0;

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
					Prop prop = new Prop(propDes, x, yIndex);
					props.add(prop);
				}
				else if (c == 'x')
				{
					String trText = triggerText.get(triggerCount);
					triggerCount++;
					Trigger trigger = new Trigger(x, yIndex, trText);
					triggers.add(trigger);							
				}
				else if (c == '?')
				{
					String pQuestion = pQuestions.get(puzzleCount);
					String pAnswer = pAnswers.get(puzzleCount);
					puzzleCount++;
					Puzzle puzzle = new Puzzle(x, yIndex, pQuestion, pAnswer);
					puzzles.add(puzzle);
				}
				else if (c == '@')
				{
					String cQuestion = ciphQuestions.get(cipherCount);
					String cHint = ciphHints.get(cipherCount);
					String cAnswer = ciphAnswers.get(cipherCount);
					cipherCount++;
					Cipher cipher = new Cipher(x, yIndex, cQuestion, cAnswer, cHint);
					ciphers.add(cipher);
				}
				else if (c == '=')
				{
					boolean vertical = false;
					door = new Door(x, yIndex, vertical);
				}
				else if (c == '|')
				{
					if (x == 1)
					{
						boolean vertical = true;
						door = new Door(x, yIndex, vertical);
					}
					else if (x == levelX - 2)
					{
						boolean vertical = true;
						door = new Door(x, yIndex, vertical);
					}
				}				
			}
		} 
		inputStream.close();
		levelY = sMap.size();
		levelX = sMap.get(0).length();
	}
	
	
	// getter method returns copy of props ArrayList
	public ArrayList<Prop> getProps()
	{
		ArrayList<Prop> tProps = new ArrayList<Prop>();
		for (int num = 0; num < props.size(); num++)
		{
			tProps.add(new Prop(props.get(num)));
		}
		return tProps;
	}
	
	// get method returns copy of triggers ArrayList
	public ArrayList<Trigger> getTriggers()
	{
		ArrayList<Trigger> tTriggers = new ArrayList<Trigger>();
		for (int num = 0; num < triggers.size(); num++)
		{
			tTriggers.add(new Trigger(triggers.get(num)));
		}
		return tTriggers;
	}
	
	public ArrayList<Puzzle> getPuzzles()
	{
		ArrayList<Puzzle> tPuzzles = new ArrayList<Puzzle>();
		for (int num = 0; num < puzzles.size(); num++)
		{
			tPuzzles.add(new Puzzle(puzzles.get(num)));
		}
		return tPuzzles;
	}
	
	// getter method returns copy of ciphers ArrayList
	public ArrayList<Cipher> getCiphers()
	{
		ArrayList<Cipher> tCiphs = new ArrayList<Cipher>();
		for (int num = 0; num < ciphers.size(); num++)
		{
			tCiphs.add(new Cipher(ciphers.get(num)));
		}
		return tCiphs;
	}
	
	
	public Door getDoor() {
		Door tDoor = new Door(door);
		return tDoor;
	}
	
	
	// getter returns player's starting X position for level
	public int getPlayerStartX()
	{
		return startX;
	}
	
	// getter returns players starting Y position for level
	public int getPlayerStartY()
	{
		return startY;
	}
	
	public String getIntroText() {
		return introText;
	}
	
	public String getExitText() {
		return exitText;
	}

	
}
