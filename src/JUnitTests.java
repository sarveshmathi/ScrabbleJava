import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

/**
 * Validation tests for the Scrabble Game
 * @author team 51
 *
 */

class JUnitTests {	
	

	/**
	 * This test will check if the given correct words are in the official online dictionary. 
	 * Will require an Internet connection and a few seconds to complete.
	 * Make sure you have the jsoup library in your project
	 */
	@Test
	public void testCorrectWord() { 
		assertEquals(true, OnlineDictionary.checkWord("hello")); //"hello" is an actual word in the dictionary
		assertEquals(true, OnlineDictionary.checkWord("OXYPHENBUTAZONE")); // Fun fact: "OXYPHENBUTAZONE" is the longest scrabble word possible
	}

	/**
	 * This test will check if the given incorrect words are in the official online dictionary. 
	 * Will require an Internet connection and a few seconds to complete.
	 * Make sure you have the jsoup library in your project
	 */
	@Test
	public void testIncorrectWord() { 
		assertEquals(false, OnlineDictionary.checkWord("sadhkajshdkasjh")); //"sadhkajshdkasjh" is not a word
		assertEquals(false, OnlineDictionary.checkWord("hism")); //"hism" is not a word
	}


	/**
	 * This test checks if a  random letter's point value is correctly assigned
	 */
	@Test
	public void testLetterPoints() {
		int sLetterPoints = LetterTilePicture.Letters.valueOf("S").points;
		assertEquals(1,sLetterPoints);
		int zLetterPoints = LetterTilePicture.Letters.valueOf("Z").points;
		assertEquals(10,zLetterPoints);

	}

	/**
	 * This test checks if a random letter's quantity in the bag is correct
	 */
	@Test
	public void testLetterQuantity() {
		int sLetterQuantity = LetterTilePicture.Letters.valueOf("S").quantity;
		assertEquals(4,sLetterQuantity);
		int zLetterQuantity = LetterTilePicture.Letters.valueOf("Z").quantity;
		assertEquals(1,zLetterQuantity);

	}

	/**
	 * This test checks if the the right number of letters 
	 * are returned from the letter bag when asked for 
	 */
	@Test
	public void testGetLetters() {
		LetterBag lb = new LetterBag();
		lb.setLettersGivenCounter(25);
		ArrayList<LetterTilePicture> ltp1 = lb.getLetters(7);
		assertEquals(7, ltp1.size()); //checks if 7 letters were returned
		ArrayList<LetterTilePicture> ltp2 = lb.getLetters(3);
		assertEquals(10, lb.getLettersGivenCounter()); //checks if counter knows that 10 letters were given in total
	}

	/**
	 * This test checks if the  bag counter is keeping track of the total number of letters given out.
	 */
	@Test
	public void testBagLetterCounter() {
		LetterBag lb = new LetterBag();
		ArrayList<LetterTilePicture> ltp2 = lb.getLetters(10);
		assertEquals(10, lb.getLettersGivenCounter()); //checks if counter knows that 10 letters were given in total
	}

	/**
	 * This test checks if a new board with proper assignment of tiles values
	 * has been successfully initialized
	 */
	@Test
	public void testBoard() {
		Board b = new Board();
		assertEquals(true, b.isBoardEmpty());
	}

	/**
	 * Checks if players are initialized with a score of 0
	 */
	@Test
	public void testPlayerScore() {
		Player p1 = new Player(1);
		assertEquals(0, 0);
	}
	
	/**
	 * This test checks if the isInListMethod from Board class works correctly
	 */
	@Test
	public void testIsInListMethod() {
		Board b = new Board();
		int[] list = new int[]{1,5,6,3,4};
		int numberToFind = 5;
		assertEquals(true, b.isInList(list, 5));
	}






}