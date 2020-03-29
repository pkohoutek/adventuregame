import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * 		LevelGenerator
 * 	Class to use a .map text file to generate a map, and .cfg
 * 	files to construct all the level's game objects attributes, including
 * 	questions, answers, and x and y positions.
 * 
 * 	Currently able to build map with walls, interactive props, 
 *  puzzles, ciphers, and triggers.
 * 	
 * 	Plan to add functionality for whatever other objects and other
 * 	features the team implements in the future
 * 
 *  Hoping some of the functionality can be incorporated when 
 *  porting code to JavaFX, we will have to hard code the 
 *  coordinates of the objects as parsing a text file will not work
 *  for the tile map or JFrame.
 */
public class LevelGenerator {
		
	// config parameters
	private int levelNumber = 0;
	private int levelX = 0;				// level x/y length for map generation
	private int levelY = 0;
	private final int X = 0, Y = 1;
	private int startX = 0;				// player x/y start position in level
	private int startY = 0;

	// Arrays for the props, walls, puzzles, story texts, triggers, etc
	private ArrayList<Prop> props;
	private ArrayList<Wall> walls; // walls for level design
	private ArrayList<Puzzle> puzzles;
	private ArrayList<Trigger> triggers; 
	private ArrayList<Cipher> ciphers;
	private ArrayList<String> descriptions;
	private ArrayList<String> triggerText;
	private ArrayList<String> pQuestions;   	// arraylist for puzzle question Strings
	private ArrayList<String> pAnswers;			// arraylist for puzzle answer Strings
	private ArrayList<String> ciphQuestions;	// arraylist for cipher questions
	private ArrayList<String> ciphAnswers;		// arraylist for cipher answers
	private ArrayList<String> ciphHints;		// array list for cipher hints
	private String introText, exitText;			// strings for level intro and exit text
	private Door door;							// level door object
	private Map map;						// generates level map
	
	// constant chars for level generation
	private final char WALL = '#', PROP = '$', TRIGGER ='x',
			PUZZLE = '?', CIPHER = '@', VDOOR = '|', HDOOR = '=';
	
	// delimiter constants for prop generation
	private final String SPROPS = "<PROPS>", EPROPS = "</PROPS>", SPROPDES = "<pdes>",
			EPROPDES = "</pdes>";
	
	// delimiter constants for trigger generation
	private final String STRIGGERS = "<TRIGGERS>", ETRIGGERS = "</TRIGGERS>",
			STRIGGERTEXT = "<trigger>", ETRIGGERTEXT = "</trigger>";
	
	// delimiter constants for puzzle generation
	private final String SPUZZLES = "<PUZZLES>", EPUZZLES = "</PUZZLES>", SPUZQUESTIONS = "<PQUESTIONS>", 
			EPUZQUESTIONS = "</PQUESTIONS>", SPUZQ = "<pquestion>", EPUZQ = "</pquestion>", 
			SPUZANSWERS = "<PANSWERS>", EPUZANSWERS = "</PANSWERS>", SPUZA = "<panswer>",
			EPUZA = "</panswer>";
	
	// delimiter constants for cipher generation
	private final String SCIPHERS = "<CIPHERS>", ECIPHERS = "</CIPHERS>", SCIPHERQS = "<CQUESTIONS>",
			ECIPHERQS = "</CQUESTIONS>", SCIPHQ = "<cquestion>", ECIPHQ = "</cquestion>",
			SCIPHERHINTS = "<CHINTS>", ECIPHERHINTS = "</CHINTS>", SCIPHH = "<chint>", ECIPHH = "</chint>",
			SCIPHERANS = "<CANSWERS>", ECIPHERANS = "</CANSWERS>", SCIPHA = "<canswer>", ECIPHA = "</canswer>";
	
	// delimiter constants for player start position
	private final String SSTART = "<START>", ESTART = "</START>";
	
	// delimiter constants for Level text generation
	private final String SLVLTEXT = "<LEVELTEXT>", ELVLTEXT = "</LEVELTEXT>", SLVLSTARTTXT = "<STARTTEXT>", ELVLSTARTTXT = "</STARTTEXT>",
			SLVLENDTXT = "<ENDTEXT>", ELVLENDTXT = "</ENDTEXT>";
	
	/**
	 * 
	 * LevelGenerator constructor
	 * @param lNumber int of level number
	 */
	public LevelGenerator(int lNumber){
		levelNumber = lNumber;
		walls = new ArrayList<Wall>();
		props = new ArrayList<Prop>();
		triggers = new ArrayList<Trigger>();
		puzzles = new ArrayList<Puzzle>();
		ciphers = new ArrayList<Cipher>();
		descriptions = new ArrayList<String>();
		triggerText = new ArrayList<String>();
		pQuestions = new ArrayList<String>();
		pAnswers = new ArrayList<String>();
		ciphQuestions = new ArrayList<String>();
		ciphHints = new ArrayList<String>();
		ciphAnswers = new ArrayList<String>();
		
		genGameObjects();
		generate();
		putObjectsInMap();
	}
	
	/**
	 * helper function puts walls and other visible objects (not triggers) in the map
	 */
	private void putObjectsInMap()
	{
		// generate map using the x and y dimensions from the .map file
		map = new Map(levelX, levelY);
		
		// iterates over the walls parsed from the level#.map file
		// and adds them to the Map.
		for (int num = 0; num < walls.size(); num++) 
		{
			Wall wall = new Wall(walls.get(num));
			map.addProp(wall);
		}
		// iterates over the level props and adds their String sprite representation
		// to the map
		for (int num = 0; num < props.size(); num++)
		{
			Prop prop = new Prop(props.get(num));
			map.addProp(prop);
		}
		// iterates over the level Puzzles and adds their String sprite representation
		// to the map
		for (int num = 0; num < puzzles.size(); num++)
		{
			Puzzle puzzle = new Puzzle(puzzles.get(num));
			map.addPuzzle(puzzle);
		}
		// iterates over the level Ciphers and adds their String sprite representation
		// to the map		
		for (int num = 0; num < ciphers.size(); num++)
		{
			Cipher cipher = new Cipher(ciphers.get(num));
			map.addPuzzle(cipher);
		}
		// adds door String sprite representation to map
		Door tDoor = new Door(door);
		map.addDoor(tDoor);
	}
	
	
	/**
	 * 	getter returns copy of the level map 
	 * @return Map copy of level map
	 */
	public Map getMap()
	{
		Map tMap = new Map(map);
		return tMap;
	}
	
	
	/**
	 * 	helper method parses the level#.cfg file (# being the number of the current level generated)
	 * generates the props, puzzles, ciphers, intro text, exit text, player position on map, and more
	 */
	private void genGameObjects() 
	{
		// create new input stream for level#.cfg file
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".cfg";
		String line = "";
		// try catch in case file doesn't exist
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
		// continue looping while the file still has lines and 
		// we have not reached the end of the Prop descriptions
		while (inputStream.hasNextLine())
		{
			line = inputStream.nextLine();
			line.trim();
			
			// if the level#.cfg file has a props section
			if (line.equals(SPROPS))
			{
				boolean endDescriptions = false;
				String description = "";
				// continue looping while we have not reached the end of the Prop descriptions
				while (!endDescriptions)
				{
					line = inputStream.nextLine();
					line.trim();
					// if the line is the delimiter indicating that we have reached the end
					// of prop descriptions, exit while loop.
					if (line.equals(EPROPS))
					{
						endDescriptions = true;
					}
					// if the line equals the delimiter indicating that we have found a new
					// Prop description, create empty string for the next lines of text
					else if  (line.equals(SPROPDES))
					{
						description = "";
					}
					// if the line equals the delimiter indicating that we have ended a
					// Prop description, add description string to prop description ArrayList
					else if  (line.equals(EPROPDES))
					{
						descriptions.add(description);
					}
					// if line is empty add a new line to description string
					else if (line.equals("\n"))
					{
						description = "" + description + "\n";	
					}
					// else add line to description string
					else
					{
						description = "" + description + line + "\n";
					}	
					
				}
			}
			// if the level#.cfg file has a trigger section
			else if (line.equals(STRIGGERS))
			{
				boolean endTrText = false;
				String trText = "";
				
				// while we haven't completed generating the Trigger text Strings
				while (!endTrText)
				{
					line = inputStream.nextLine();
					line.trim();
					// if we find the end trigger text delimiter we are done
					if (line.equals(ETRIGGERS))
					{
						endTrText = true;
					}
					// if we find the start of a new trigger text string
					// initialize an empty string
					else if  (line.equals(STRIGGERTEXT))
					{
						trText = "";
					}
					// if we find the end of the current trigger text string
					// add the trigger text string to the ArrayList
					else if  (line.equals(ETRIGGERTEXT))
					{
						triggerText.add(trText);
					}
					// if the current line is a new line
					// append it to the current string
					else if (line.equals("\n"))
					{
						trText = "" + trText + "\n";	
					}
					// else add the current line to the string
					else
					{
						trText = "" + trText + line + "\n";
					}					
				}
			}
			
			// if the level has a puzzles section
			else if (line.equals(SPUZZLES))
			{				
				boolean endPuzText = false;
				String puzQText = "";
				String puzAText = "";

				// while there is another line in the .cfg file and
				// we haven't finished generating puzzle text
				while (!endPuzText)
				{
					line = inputStream.nextLine();
					line.trim();
					// if we find delimiter that indicates we are in the 
					// Puzzle Questions section of the level#.cfg file
					if (line.equals(SPUZQUESTIONS))
					{
						boolean endQuestions = false;
						// while we have not reached the end of the puzzle questions
						// section.
						while (!endQuestions)
						{
							line = inputStream.nextLine();
							line.trim();
							// if we have reached the end of the puzzle questions
							// section, we are done.
							if (line.equals(EPUZQUESTIONS))
							{
								endQuestions = true;
							}
							// if we find a new puzzle question string
							// create empty string
							else if (line.equals(SPUZQ))
							{
								puzQText = "";
							}
							// if we find the end of the current puzzle question
							// string, add it to the puzzle question arraylist
							else if (line.equals(EPUZQ))
							{
								pQuestions.add(puzQText);
							}
							// if we find a new line, append it to the current string
							else if (line.equals("\n"))
							{
								puzQText = "" + puzQText + "\n";	
							}
							// else add current line to string
							else
							{
								puzQText = "" + puzQText + line + "\n";
							}
						}
					}
					// if we find delimiter that indicates we are in the 
					// Puzzle Answers section of the level#.cfg file
					else if (line.equals(SPUZANSWERS))
					{							
						boolean endAnswers = false;
						while (!endAnswers)
						{
							line = inputStream.nextLine();
							line.trim();
							// if we have found the delimiter indicating that we have reached the end
							// of the puzzle section we are done.
							if (line.equals(EPUZANSWERS))
							{
								endAnswers = true;
							}
							// if we find the delimiter for a new puzzle answer
							// create a new empty string
							else if  (line.equals(SPUZA))
							{
								puzAText = "";
							}
							// if we have reached the end of the current puzzle answer string
							// add it to the arraylist
							else if  (line.equals(EPUZA))
							{
								pAnswers.add(puzAText);
							}
							// if we find a new line, append it to the string
							else if (line.equals("\n"))
							{
								puzAText = "" + puzAText + "\n";	
							}
							// else add current line to the string
							else
							{
								puzAText = "" + puzAText + line;
							}
						}
					}	
					// if we find the end of puzzles delimiter we are done with the 
					// levels puzzles
					else if (line.equals(EPUZZLES))
					{
						endPuzText = true;
					}		
				}
			}
			// if we find the delimiter indicating the start of the cipher section of
			// the level#.cfg file
			else if (line.equals(SCIPHERS))
			{
				boolean endCiphText = false;
				 String ciphQText = "";
				// while we haven't reached the end of the cipher text
				while (!endCiphText)
				{
					line = inputStream.nextLine();
					line.trim();
					// if we find the delimiter indicating we are in the Cipher
					// Questions section of the .cfg file
					if (line.equals(SCIPHERQS))
					{	
						boolean endQuestions = false;
						// while not at the end of the cipher queston section
						while (!endQuestions)
						{
							line = inputStream.nextLine();
							line.trim();
							// if we find the delimiter indicating we are at the end of the
							// cipher question section we are done
							if (line.equals(ECIPHERQS))
							{
								endQuestions = true;
							}
							// if we find the delimiter indicating we have started a new
							// cipher question, create an empty string
							else if  (line.equals(SCIPHQ))
							{
								ciphQText = "";
							}
							// if we find the delimiter indicating that we have ended the 
							// current cipher question, add the string to the arraylist
							else if  (line.equals(ECIPHQ))
							{
								ciphQuestions.add(ciphQText);
							}
							// if we find a new line, append it to the current string
							else if (line.equals("\n"))
							{
								ciphQText = "" + ciphQText + "\n";	
							}
							// else add line to current string
							else
							{
								ciphQText = "" + ciphQText + line + "\n";
							}
						}
					}
					// if we find the delimiter for the cipher hints section of the file
					else if (line.equals(SCIPHERHINTS))
					{
						String ciphHText = "";
						boolean endHints = false;
						// while we haven't reached the end of the cipher hints section
						while (!endHints)
						{
							line = inputStream.nextLine();
							line.trim();
							// if we find the delimiter indicating  we have reached the end
							// of the cipher hints section we are done
							if (line.equals(ECIPHERHINTS))
							{
								endHints = true;
							}
							// if we have a new cipher hint string to generate
							// empty string
							else if  (line.equals(SCIPHH))
							{
								ciphHText = "";
							}
							// if we reached the end of the current cipher hint text
							// add string to cipher hints arraylist
							else if  (line.equals(ECIPHH))
							{
								ciphHints.add(ciphHText);
							}
							// if we find a new line, append it to string
							else if (line.equals("\n"))
							{
								ciphHText = "" + ciphHText + "\n";	
							}
							// else add line to string
							else
							{
								ciphHText = "" + ciphHText + line + "\n";
							}
						}
					}	
					// if we find the start of the cipher answers section
					else if (line.equals(SCIPHERANS))
					{
						boolean endAnswers = false;
						String ciphAText = "";
						// while we have not reached the end of cipher answers section
						while (!endAnswers)
						{
							line = inputStream.nextLine();
							line.trim();
							// if we find the delimiter indicating we are at the end of
							// cipher answers we are done
							if (line.equals(ECIPHERANS))
							{
								endAnswers = true;
							}
							// if we find the delimiter for a new Cipher answer string, make empty
							// string
							else if  (line.equals(SCIPHA))
							{
								ciphAText = "";
							}
							// if we find the delimiter indicating we have reached the end of the current
							// cipher answer string, add it to the arraylist
							else if  (line.equals(ECIPHA))
							{
								ciphAnswers.add(ciphAText);
							}
							// if the next line is a new line, append the new line 
							// to the current string
							else if (line.equals("\n"))
							{
								ciphAText = "" + ciphAText + "\n";	
							}
							// else add the line to the current string
							else
							{
								ciphAText = "" + ciphAText + line;   // no new line to avoid String equals not working
							}
						}
					}		
					// if we reached the end of the cipher section we are done
					if (line.equals(ECIPHERS))
					{
						endCiphText = true;
					}
				}					
			}
			// if we find the player start position section of the 
			// level.cfg file
			else if (line.equals(SSTART))
			{
				boolean endStart = false;
				int count = 0;
				// while we haven't reached the end of the player start
				// section in the .cfg file
				while(!endStart)
				{
					line = inputStream.nextLine();
					line.trim();
					// if we find delimited indicating end of player start section
					// we are done
					if (line.equals(ESTART))
					{
						endStart = true;
					}
					// if count is 0, we are at first line which is X value
					else if (count == X)
					{
						// try catch for number format exception if someone slips a non numeric
						// character in config file
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
					// else if count is 1 we are at the player start y value for level
					else if (count == Y){
						// try catch for number format exception errors if a non-numeric character is in the line
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
			// if we have found the start of the level introduction 
			// and ending text section
			else if (line.equals(SLVLTEXT))
			{
				boolean endLevelText = false;
				String tIntroText = "";
				String tExitText = "";
				// while we haven't reached the end of the file and have not finished generating
				// level text
				while (!endLevelText)
				{
					line = inputStream.nextLine();
					line.trim();
					// if we find the delimiter for the level introduction text string
					if (line.equals(SLVLSTARTTXT))
					{
						boolean endIntro = false;
						// while we haven't reached the end of the introduction text section
						while (!endIntro)
						{
							line = inputStream.nextLine();
							line.trim();
							// if we have reached the end of the introduction text section we are done
							if (line.equals(ELVLSTARTTXT))
							{
								endIntro = true;
							}
							// else if there is a new line character append it to the current string
							else if (line.equals("\n"))
							{
								tIntroText = "" + tIntroText + "\n";	
							}
							// else append current line to string
							else
							{
								tIntroText = "" + tIntroText + line + "\n";
							}
						}
					}
					// if we found the delimiter indicating we have reached the level end text section
					if (line.equals(SLVLENDTXT))
					{
						boolean endEnding = false;
						// while we haven't reached the ending of the level end text section
						while (!endEnding)
						{
							line = inputStream.nextLine();
							line.trim();
							// if we reached the end of the level end text section we are done
							if (line.equals(ELVLENDTXT))
							{
								endEnding = true;
							}
							// if line is a new line append it to string
							else if (line.equals("\n"))
							{
								tExitText = "" + tExitText + "\n";	
							}
							// else append line to string
							else
							{
								tExitText = "" + tExitText + line + "\n";
							}
						}
					}
					// if we reached the end of the level text section we are done
					if (line.equals(ELVLTEXT))
					{
						endLevelText = true;
					}
				}
				introText = tIntroText;
				exitText = tExitText;
			}			
		}		
		inputStream.close();
	}	

	
	/**
	 * helper method generates walls, props, puzzles, ciphers, and triggers
	 * based on chars from .map file and adds the constructor parameters
	 * from ArrayLists generated in other methods of this class.
	 */
	private void generate() {
		walls = new ArrayList<Wall>();
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".map";
		// temporary array lists
		ArrayList<String> sMap = new ArrayList<String>();
		ArrayList<String> tMap = new ArrayList<String>();
		// counters to access elements in the arraylists for each object
		int propCount = 0, triggerCount = 0, puzzleCount = 0, cipherCount = 0;
		// try catch for file not found exceptions
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
		// generate string arrayList with each line of .map file
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
		// iterate over y rows in map
		for (int y = 0; y < sMap.size(); y++)
		{	
			// iterator over x columns in map
			for (int x = 0; x < sMap.get(y).length(); x++)
			{
				// get the current character (x,y) in map from the arraylist
				char c = sMap.get(y).charAt(x);
				// if character is a wall, generate a wall obect at the x,y position
				if (c == WALL)
				{
					Wall wall = new Wall(x, y);
					walls.add(wall);
				}
				// if character is a prop, generate a prop at the x/y location
				else if (c == PROP)
				{
					String propDes = descriptions.get(propCount);
					propCount++;
					Prop prop = new Prop(propDes, x, y);
					props.add(prop);
				}
				// if character is a trigger, generate a trigger at the x/y location
				else if (c == TRIGGER)
				{
					String trText = triggerText.get(triggerCount);
					triggerCount++;
					Trigger trigger = new Trigger(x, y, trText);
					triggers.add(trigger);							
				}
				// if character is a puzzle, generate a puzzle at the x/y location
				else if (c == PUZZLE)
				{
					String pQuestion = pQuestions.get(puzzleCount);
					String pAnswer = pAnswers.get(puzzleCount);
					puzzleCount++;
					Puzzle puzzle = new Puzzle(x, y, pQuestion, pAnswer);
					puzzles.add(puzzle);
				}
				// if character is a cipher generate cipher puzzle at x/y location
				else if (c == CIPHER)
				{
					String cQuestion = ciphQuestions.get(cipherCount);
					String cHint = ciphHints.get(cipherCount);
					String cAnswer = ciphAnswers.get(cipherCount);
					cipherCount++;
					Cipher cipher = new Cipher(x, y, cQuestion, cAnswer, cHint);
					ciphers.add(cipher);
				}
				// if character is a horizontal door "=" 
				// generate door at x/y location
				else if (c == HDOOR)
				{
					boolean vertical = false;
					door = new Door(x, y, vertical);
				}
				// if character is a vertical door '|'
				// generate door at x/y position
				else if (c == VDOOR)
				{
					// if the door is at postion x = 1 
					// then it is the door we can interact with
					// x = 0 means its not accessible due to collision with the door
					if (x == 1)
					{
						boolean vertical = true;
						door = new Door(x, y, vertical);
					}
					// if x is two to the left of the end of the map it is a door we can interact
					// with and we should generate it
					else if (x == sMap.get(y).length() - 2)
					{
						boolean vertical = true;
						door = new Door(x, y, vertical);
					}
				}				
			}
		} 
		inputStream.close();
		levelY = sMap.size();
		levelX = sMap.get(X).length();
	}
	
	
	/**
	 * getter method returns copy of props ArrayList
	 * @return Prop ArrayList for the level
	 */
	public ArrayList<Prop> getProps()
	{
		ArrayList<Prop> tProps = new ArrayList<Prop>();
		for (int num = 0; num < props.size(); num++)
		{
			tProps.add(new Prop(props.get(num)));
		}
		return tProps;
	}
	
	/**
	 * getter method returns copy of triggers ArrayList
	 * @return Trigger ArrayList for the level
	 */
	public ArrayList<Trigger> getTriggers()
	{
		ArrayList<Trigger> tTriggers = new ArrayList<Trigger>();
		for (int num = 0; num < triggers.size(); num++)
		{
			tTriggers.add(new Trigger(triggers.get(num)));
		}
		return tTriggers;
	}
	
	/**
	 * getter method returns copy of puzzles arraylist
	 * @return Puzzle ArrayList for the level
	 */
	public ArrayList<Puzzle> getPuzzles()
	{
		ArrayList<Puzzle> tPuzzles = new ArrayList<Puzzle>();
		for (int num = 0; num < puzzles.size(); num++)
		{
			tPuzzles.add(new Puzzle(puzzles.get(num)));
		}
		return tPuzzles;
	}
	
	/**
	 * getter method returns copy of ciphers ArrayList
	 * @return Cipher ArrayList for the level
	 */
	public ArrayList<Cipher> getCiphers()
	{
		ArrayList<Cipher> tCiphs = new ArrayList<Cipher>();
		for (int num = 0; num < ciphers.size(); num++)
		{
			tCiphs.add(new Cipher(ciphers.get(num)));
		}
		return tCiphs;
	}
	
	/**
	 * getter method returns door
	 * @return Door object for the level
	 */
	public Door getDoor() {
		Door tDoor = new Door(door);
		return tDoor;
	}
	
	
	/**
	 * getter returns player's starting X position for level
	 * @return int of player's x starting position in level
	 */
	public int getPlayerStartX()
	{
		return startX;
	}
	
	/**
	 * getter returns players starting Y position for level
	 * @return int of player's y starting position in level
	 */
	public int getPlayerStartY()
	{
		return startY;
	}
	
	/**
	 * getter method for level introduction string
	 * @return String of level introduction text
	 */
	public String getIntroText() {
		return introText;
	}
	
	/**
	 * getter method for level ending string
	 * @return String of level exit text
	 */
	public String getExitText() {
		return exitText;
	}
	
	
}
