/**
 * Represents a standard letter tile
 * @author sarvesh
 *
 */
public class LetterTile {
	private String letter;
	private int value;
	
	/**
	 * Initialized a LetterTile with given letter and value
	 * @param letter
	 * @param value
	 */
	public LetterTile(String letter, int value) {
		this.letter = letter;
		this.value = value;
	}

	public String getLetter() {
		return letter;
	}

	public int getValue() {
		return value;
	}
}
