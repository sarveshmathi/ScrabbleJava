import java.util.ArrayList;
import java.util.Collections;

/**
 * A letter bag with 98 letters (actual game has 100 but we exclude blank tiles here)
 * @author sarvesh
 *
 */

public class LetterBag {

	private ArrayList<LetterTile> letterTiles; 
	private int lettersGivenCounter; //Keeps track of how many letters are given out in a game, also the position in letterTiles ArrayList. 

	public LetterBag() {
		letterTiles = new ArrayList<>();
		initializeBag();
		Collections.shuffle(letterTiles); //shuffles the bag
		lettersGivenCounter = 0;
	}

	public int getLettersGivenCounter() {
		return lettersGivenCounter;
	}

	public void setLettersGivenCounter(int lettersGivenCounter) {
		this.lettersGivenCounter = lettersGivenCounter;
	}

	public ArrayList<LetterTile> getLetterTiles() {
		return letterTiles;
	}

	/**
	 * Puts 98 letter tiles in the letterTiles ArrayList based on the 
	 * letter distribution and values obtained from https://scrabble.hasbro.com/en-us/faq
	 */
	public void initializeBag() {
		//
		//Put 12 letter E's (value 1)
		for (int i = 0; i < 12; i++) {
			letterTiles.add(new LetterTile("E",1));
		}
		//Put 9 letter A's and I's (value 1)
		for (int i = 0; i < 9; i++) {
			letterTiles.add(new LetterTile("A",1));
			letterTiles.add(new LetterTile("I",1));
		}
		//Put 8 letter O's (value 1)
		for (int i = 0; i < 8; i++) {
			letterTiles.add(new LetterTile("O",1));
		}
		//Put 6 letter N's, R's and T's (value 1)
		for (int i = 0; i < 6; i++) {
			letterTiles.add(new LetterTile("N",1));
			letterTiles.add(new LetterTile("R",1));
			letterTiles.add(new LetterTile("T",1));
		}
		//Put 4 letter D's (value 2), L's, S's and U's (value 1)
		for (int i = 0; i < 4; i++) {
			letterTiles.add(new LetterTile("D",2));
			letterTiles.add(new LetterTile("L",1));
			letterTiles.add(new LetterTile("S",1));
			letterTiles.add(new LetterTile("U",1));
		}
		//Put 3 G's (value 2)
		for (int i = 0; i < 3; i++) {
			letterTiles.add(new LetterTile("G",2));
		}
		//Put 2 B's, C's (value 3), F's, H's (value 4), M's, P's (value 3), V's, W's, and Y's (value 4)
		for (int i = 0; i < 2; i++) {
			letterTiles.add(new LetterTile("B",3));
			letterTiles.add(new LetterTile("C",3));
			letterTiles.add(new LetterTile("F",4));
			letterTiles.add(new LetterTile("H",4));
			letterTiles.add(new LetterTile("M",3));
			letterTiles.add(new LetterTile("P",3));
			letterTiles.add(new LetterTile("V",4));
			letterTiles.add(new LetterTile("W",4));
			letterTiles.add(new LetterTile("Y",4));
		}
		//Put 1 J (value 8), K (value 5), Q (value 10), X (value 8), Z (value 10)
		for (int i = 0; i < 1; i++) {
			letterTiles.add(new LetterTile("J",8));
			letterTiles.add(new LetterTile("K",5));
			letterTiles.add(new LetterTile("Q",10));
			letterTiles.add(new LetterTile("X",8));
			letterTiles.add(new LetterTile("Z",10));
		}
	}
	
	/**
	 * Returns an ArrayList of random LetterTiles of size noOfLetters
	 * @param noOfLetters
	 * @return
	 */
	ArrayList<LetterTile> getLetters(int noOfLetters) {
		ArrayList<LetterTile> output = new ArrayList<>();
		for (int i = 0; i < noOfLetters && lettersGivenCounter < 98; i++) {
			output.add(letterTiles.get(lettersGivenCounter));
			lettersGivenCounter++;
		}
		return output;	
	}
	
//	public static void main(String[] args) {
//		LetterBag lb = new LetterBag();
//		System.out.println("Number of letter tiles = " + lb.letterTiles.size());
//		ArrayList<LetterTile> lettersOne = lb.getLetters(7);
//		System.out.println("No. of letters given out = " + lettersOne.size());
//		System.out.println("No. of letters given out (index counter) = " + lb.lettersGivenCounter);
//		ArrayList<LetterTile> lettersTwo = lb.getLetters(5);
//		System.out.println("No. of letters given out = " + lettersTwo.size());
//		System.out.println("No. of letters given out (index counter) = " + lb.lettersGivenCounter);
//		for (int i = 0; i < lettersOne.size(); i++) {
//			System.out.println(lettersOne.get(i).getLetter() + " " + lettersOne.get(i).getValue()); 
//		}
//	}

}
