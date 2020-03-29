/**
 * 	Move Enumerator 
 * 	Used for player movement and String
 * 	player avatar "animation"
 */
public enum Move {
	
	UP(1), DOWN(2), LEFT(3), RIGHT(4), HITLEFT(5), 
	HITRIGHT(6), HITUPDOWN(7), IDLE(8);
	
	private int direction;
	
	/**
	 * exploring using methods and attributes in enums in Java,
	 * this enum is not fully implemented.
	 * @param direction int representing direction
	 */
	private Move(int direction){
		this.direction = direction;
	}
	
}
