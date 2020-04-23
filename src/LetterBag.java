import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a letter bag with 98 letters (actual game has 100 but
 * we exclude blank tiles here).
 * 
 * @author Team 51
 *
 */

public class LetterBag {
	public ArrayList<LetterTilePicture> letterTiles = new ArrayList<LetterTilePicture>();
	private int lettersGivenCounter; // Keeps track of how many letters are given out in a game, also the position in
										// bag
	public LetterTilePicture letterTilePicture;

	public LetterBag() {
		// this loop goes over the enum and puts the correct quanity of each letter into
		// the bag
		for (LetterTilePicture.Letters letters : LetterTilePicture.Letters.values()) {
			int lettersPutCounter = 0;
			while (lettersPutCounter < letters.quantity) {
				LetterTilePicture addToBag = new LetterTilePicture(letters);
				letterTiles.add(addToBag);
				lettersPutCounter++;
			}
		}

		Collections.shuffle(letterTiles); // shuffles the bag
		lettersGivenCounter = 0;
	}

	public int getLettersGivenCounter() {
		return lettersGivenCounter;
	}

	public void setLettersGivenCounter(int lettersGivenCounter) {
		this.lettersGivenCounter = lettersGivenCounter;
	}

	public ArrayList<LetterTilePicture> getLetterTiles() {
		return letterTiles;
	}

	/**
	 * Returns an ArrayList of random LetterTiles of size numberOfLetters
	 * 
	 * @param numberOfLetters - the number of letters
	 * @return output - ArrayList of letters
	 */
	public ArrayList<LetterTilePicture> getLetters(int numberOfLetters) {
		ArrayList<LetterTilePicture> output = new ArrayList<LetterTilePicture>();
		for (int i = 0; i < numberOfLetters && lettersGivenCounter < 98; i++) {
			output.add(letterTiles.get(lettersGivenCounter));
			lettersGivenCounter++;
		}
		return output;
	}

}
