import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Validation tests for the Scrabble Game
 * @author sarvesh
 *
 */

class JUnitTests {	

	@Test 
	public void testLetterBagClass() {
		LetterBag lb = new LetterBag();
		assertEquals(98, lb.getLetterTiles().size()); //check if there are 98 tiles
		ArrayList<LetterTile> letters = lb.getLetters(7); //get seven letters from the bag
		assertEquals(7, letters.size()); //check if 7 letters were returned
		assertEquals(7, lb.getLettersGivenCounter()); //check if letterCounter is working correctly
	}
	
	/**
	 * This test will check if the given words are in the official online dictionary. 
	 * Will require an Internet connection and a few seconds to complete.
	 * Make sure you have the jsoup library in your project
	 */
	@Test
	public void testOnlineDictionaryClass() { 
		OnlineDictionary od = new OnlineDictionary();
		assertEquals(true, od.checkWord("hello")); //"hello" is an actual word in the dictionary
		assertEquals(false, od.checkWord("sadhkajshdkasjh")); //"sadhkajshdkasjh" is not a word
		assertEquals(true, od.checkWord("OXYPHENBUTAZONE")); // Fun fact: "OXYPHENBUTAZONE" is the longest scrabble word possible
		assertEquals(false, od.checkWord("hism")); //"hism" is not a word
	}
	
	
	
}
