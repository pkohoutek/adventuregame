// import Hashmap
import java.util.HashMap;

/**
 * Animator class
 * Provides limited animation by changing the player
 * avatar's string representation based on direction moved
 * and if they have collided with another object
 * @author Paul
 *
 */
public class Animator {
	
	// string array containing the player "animation sprites"
	private String[] sSprites = {
		"<(^.^)>", "<('.')>", "<('.'<)", "(>'.')>",
		"<(*-*<)", "(>*-*)>", "<(*.*)>", "<(-.-)>"
	};
	
	// using HashMap to store String Array actions below as keys with numeric vales for the 
	// elements in the above sSprites String array.
	private HashMap<Move, Integer> sprites;
	

	
	/**
	 * 	constructor for Animator object
	 */
	public Animator() {
		sprites = new HashMap<Move, Integer>();
		generateActions();
	}
	
	/**
	 * generateActions() assigns the index numbers of the string array actions 
	 * to use in the getSprite() methods below to improve readability 
	 * and a good excuse to learn about hashmaps in Java
	 */
	private void generateActions() {
		int index = 0;
		for (Move move : Move.values())
		{
			sprites.put(move, index);
			index++;
		}
	}	
	
	/**
	 * getter takes player move integer and boolean if they hit an object and
	 * returns string representation of sprite.
	 * @param move enum for direction of player movement
	 * @return String of player avatar based on direction moved or if a collision occured
	 */
	public String getSprite(Move move)
	{
		String sSprite = "";
		// using a switch to assign string array index to "sprite" to return to player
		// a simple animation solution 
		switch(move)
		{
		case UP:
			sSprite = sSprites[sprites.get(Move.UP)];
			break;
		case DOWN:
			sSprite = sSprites[sprites.get(Move.DOWN)];
			break;
		case LEFT:
			sSprite = sSprites[sprites.get(Move.LEFT)];
			break;
		case RIGHT:
			sSprite = sSprites[sprites.get(Move.RIGHT)];
			break;
		case HITLEFT:
			sSprite = sSprites[sprites.get(Move.HITLEFT)];
			break;
		case HITRIGHT:
			sSprite = sSprites[sprites.get(Move.HITRIGHT)];
			break;
		case HITUPDOWN:
			sSprite = sSprites[sprites.get(Move.HITUPDOWN)]; 
			break;
		case IDLE:
			sSprite = sSprites[sprites.get(Move.IDLE)];
			break;
		default:
			sSprite = sSprites[sprites.get(Move.IDLE)];
			break;
		}
		return sSprite;
	}
	
}
