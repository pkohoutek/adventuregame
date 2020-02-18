import java.util.HashMap;

public class Animator {
	
	// string array containing the player "animation sprites"
	private String[] sSprites = {
		"<(^.^)>", "<('.')>", "<('.'<)", "(>'.')>",
		"<(*-*<)", "(>*-*)>", "<(*.*)>", "<(-.-)>"
	};
	
	// using HashMap to store String Array actions below as keys with numeric vales for the 
	// elements in the above sSprites String array.
	private HashMap<String, Integer> actions;
	
	// String array containing player actions
	private final String[] S_ACTIONS = {
		"UP", "DOWN", "LEFT", "RIGHT", "HITLEFT", "HITRIGHT", "HITUPDOWN", "IDLE"
	};
	
	
	// constructor for Animator object
	public Animator() {
		actions = new HashMap<String, Integer>();
		generateActions();
	}
	
	// assigns the index numbers of the string array actions 
	// to use in the getSprite() methods below to improve readability 
	// and a good excuse to learn about hashmaps
	private void generateActions() {
		for (int num = 0; num < S_ACTIONS.length; num++)
		{
			actions.put(S_ACTIONS[num], num);
		}
	}
	
	// takes player move integer and boolean if they hit an object and
	// returns string representation of sprite.
	public String getSprite(int move, boolean hitObject)
	{
		// if the player is moving up or down but is blocked by an object or end of map string array
		if ((move == 1 || move == 2 ) && hitObject){
			move = 7;   // move 7 represents the element number that the player has hit an object moving up or down
		}
		// if the player is moving left and hit an object
		else if (move == 3 && hitObject){
			move = 5;	// move 5 represents the element number that the player has hit an object moving left
		}
		// if the player is moving right and hit an object
		else if(move == 4 && hitObject)
		{
			move = 6;	// move 6 represents the number that the player has hit an object moving right. 
		}
		String sSprite = "";
		// using a switch to assign string array index to "sprite" to return to player
		// would like to find a better solution than using magic numbers here, thinking about
		// how to use enum or use constants;
		switch(move)
		{
		case 1:
			sSprite = sSprites[actions.get("UP")];
			break;
		case 2:
			sSprite = sSprites[actions.get("DOWN")];
			break;
		case 3:
			sSprite = sSprites[actions.get("LEFT")];
			break;
		case 4:
			sSprite = sSprites[actions.get("RIGHT")];
			break;
		case 5:
			sSprite = sSprites[actions.get("HITLEFT")];
			break;
		case 6:
			sSprite = sSprites[actions.get("HITRIGHT")];
			break;
		case 7:
			sSprite = sSprites[actions.get("HITUPDOWN")]; 
			break;
		case 8:
			sSprite = sSprites[actions.get("IDLE")];
			break;
		default:
			sSprite = sSprites[actions.get("IDLE")];
			break;
		}
		return sSprite;
	}
	
	// takes player move integer and
	// returns string representation of sprite.
	public String getSprite(int move)
	{
		String sSprite = "";
		switch(move)
		{
		case 1:
			sSprite = sSprites[actions.get("UP")];
			break;
		case 2:
			sSprite = sSprites[actions.get("DOWN")];
			break;
		case 3:
			sSprite = sSprites[actions.get("LEFT")];
			break;
		case 4:
			sSprite = sSprites[actions.get("RIGHT")];
			break;
		case 5:
			sSprite = sSprites[actions.get("HITLEFT")];
			break;
		case 6:
			sSprite = sSprites[actions.get("HITRIGHT")];
			break;
		case 7:
			sSprite = sSprites[actions.get("HITUPDOWN")]; 
			break;
		case 8:
			sSprite = sSprites[actions.get("IDLE")];
			break;
		default:
			sSprite = sSprites[actions.get("IDLE")];
			break;
		}
		return sSprite;
	}		
}
