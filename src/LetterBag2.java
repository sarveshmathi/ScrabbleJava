import java.util.ArrayList;
import java.util.Collections;

/**
 * A letter bag with 98 letters (actual game has 100 but we exclude blank tiles
 * here)
 * 
 * @author Team 51
 *
 */

public class LetterBag2 {
	private ArrayList<LetterTilePic> letterTiles = new ArrayList<LetterTilePic>();
	private int lettersGivenCounter; // Keeps track of how many letters are given out in a game, also the position in bag								
	private LetterTilePic lp;

	public LetterBag2() {
		//this loop goes over the enum and puts the correct quanity of each letter into the bag
		for (LetterTilePic.Letters letters : LetterTilePic.Letters.values()) {
			int lettersPutCounter = 0;
			while (lettersPutCounter < letters.quantity) {
				LetterTilePic addToBag = new LetterTilePic(letters);
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

	public ArrayList<LetterTilePic> getLetterTiles() {
		return letterTiles;
	}

	/**
	 * Returns an ArrayList of random LetterTiles of size noOfLetters
	 * 
	 * @param noOfLetters
	 * @return ArrayList of letters
	 */
	public ArrayList<LetterTilePic> getLetters(int noOfLetters) {
		ArrayList<LetterTilePic> output = new ArrayList<LetterTilePic>();
		for (int i = 0; i < noOfLetters && lettersGivenCounter < 100; i++) {
			output.add(letterTiles.get(lettersGivenCounter));
			lettersGivenCounter++;
		}
		return output;
	}

	// public LetterTilePic deal() {

	// LetterTilePic upnext = letter[0];// begins dealing the 1st card
	// for (int i = 1; i < cardStorer.length; i++) {
	// cardStorer[i - 1] = cardStorer[i];// moves every character up one place in
	// the array
	// }
	// return upnext;
	// }

}
