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
		assertEquals("Accepted,16", Scoring.checkInput(words));
	}
	
	
	
}
