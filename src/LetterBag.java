import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a letter bag with 98 letters (actual game has 100 but we exclude blank tiles
 * here).
 * 
 * @author Team 51
 *
 */

public class LetterBag {
	public ArrayList<LetterTilePicture> letterTiles = new ArrayList<LetterTilePicture>();
	private int lettersGivenCounter; // Keeps track of how many letters are given out in a game, also the position in
										// bag
	public LetterTilePicture letterTilePic;

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
	 * Returns an ArrayList of random LetterTiles of size noOfLetters
	 * 
	 * @param noOfLetters - the number of letters
	 * @return output - ArrayList of letters
	 */
	public ArrayList<LetterTilePicture> getLetters(int noOfLetters) {
		ArrayList<LetterTilePicture> output = new ArrayList<LetterTilePicture>();
		for (int i = 0; i < noOfLetters && lettersGivenCounter < 98; i++) {
			output.add(letterTiles.get(lettersGivenCounter));
			lettersGivenCounter++;
		}
		return output;
	}
	
	/**
	 * This method resets the player's rack.
	 * @param player - the current player.
	 */
	public void resetrack(Player player) {
		for (LetterTilePicture lp : player.rackletters) {
			player.rackletters.remove(lp);
			letterTiles.add(lp);
		}
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
