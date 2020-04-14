import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Validation tests for the Scrabble Game
 * @author sarvesh
 *
 */

class JUnitTests {	

	
	/**
	 * This test will check if the given words are in the official online dictionary. 
	 * Will require an Internet connection and a few seconds to complete.
	 * Make sure you have the jsoup library in your project
	 */
	@Test
	public void testOnlineDictionaryClass() { 
		assertEquals(true, OnlineDictionary.checkWord("hello")); //"hello" is an actual word in the dictionary
		assertEquals(false, OnlineDictionary.checkWord("sadhkajshdkasjh")); //"sadhkajshdkasjh" is not a word
		assertEquals(true, OnlineDictionary.checkWord("OXYPHENBUTAZONE")); // Fun fact: "OXYPHENBUTAZONE" is the longest scrabble word possible
		assertEquals(false, OnlineDictionary.checkWord("hism")); //"hism" is not a word
	}
	
	/**
	 * This method tests if the scoring works correctly by using the predetermined words "hello" and "bye"
	 * which are both in the dictionary and have a total score of 16 points
	 */
	@Test
	public void testScoring() {
		ArrayList<String> words = new ArrayList<String>();
		words.add("hello");
		words.add("bye");
		assertEquals(16, Scoring.checkInput(words));
	}
	
	/**
	 * This test randomly check if a letter's point and quantity are correct
	 */
	@Test
	public void testLetterPointsAndQuanity() {
		int sLetterPoints = LetterTilePic.Letters.valueOf("S").points;
		assertEquals(1,sLetterPoints);
		int sLetterQuantity = LetterTilePic.Letters.valueOf("S").quantity;
		assertEquals(4,sLetterQuantity);
		int zLetterPoints = LetterTilePic.Letters.valueOf("Z").points;
		assertEquals(10,zLetterPoints);
		int zLetterQuantity = LetterTilePic.Letters.valueOf("Z").quantity;
		assertEquals(1,zLetterQuantity);
	}
	
	/**
	 * This test checks if the the right number of letters are returned from the letter bag when asked for 
	 * and the bag counter is keeping track of the total number of letters remaining.
	 */
	@Test
	public void testGetLetters() {
		LetterBag2 lb = new LetterBag2();
		ArrayList<LetterTilePic> ltp1 = lb.getLetters(7);
		assertEquals(7, ltp1.size()); //checks if 7 letters were returned
		ArrayList<LetterTilePic> ltp2 = lb.getLetters(3);
		assertEquals(10, lb.getLettersGivenCounter()); //checks if counter knows that 10 letters were given in total
		
	}
	
	
	
	
	
}
