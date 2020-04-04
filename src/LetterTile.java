/**
 * Represents a standard letter tile
 * @author Team 51
 *
 */
public class LetterTile {
	private String letter;
	private int value;
	
	/**
	 * Initialized a LetterTile with given letter and value
	 * @param letter - letter (A-Z) of the tile
	 * @param value - value of the tile
	 */
	public LetterTile(String letter, int value) {
		this.letter = letter;
		this.value = value;
	}

	/**
	 * This method gets the letter of tile.
	 * @return letter - the letter
	 */
	public String getLetter() {
		return letter;
	}

	/**
	 * This method gets the value of tile.
	 * @return value - the value
	 */
	public int getValue() {
		return value;
	}
}
