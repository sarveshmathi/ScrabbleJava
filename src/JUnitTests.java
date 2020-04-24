import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import application.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Validation tests for the Scrabble Game
 * @author team 51
 *
 * IMPORTANT NOTE: 
 * THIS TEST WILL LAUNCH THE APPLICATION TO START SOME JAVAFX COMPONENTS. 
 * CLOSE THE WINDOW TO CONTINUE WITH THE TESTS.
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class JUnitTests {	
	
	//private static Board b = new Board();
	
	/**
	 * This test checks if the the right number of letters 
	 * are returned from the letter bag when asked for.
	 */
	@Test
	@Order(1)
	public void testGetLetters() {
		Application.launch(BoardDisplay.class, new String[0]);
		LetterBag lb = new LetterBag();
		ArrayList<LetterTilePicture> ltp1 = lb.getLetters(7);
		assertEquals(7, ltp1.size()); //checks if 7 letters were returned
	
	}
	
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
	 * This test checks if the  bag counter is keeping track of the total number of letters given out.
	 */
	@Test
	public void testBagLetterCounter() {
		LetterBag lb = new LetterBag();
		lb.setLettersGivenCounter(10); //set the counter to 10
		ArrayList<LetterTilePicture> ltp2 = lb.getLetters(10); //ask bag for 10 letters
		assertEquals(20, lb.getLettersGivenCounter()); //checks if counter knows that 20 letters were given in total
	}
	
	/**
	 * Tests if the a board with proper assignment of tile values has been successfully initialized
	 */
	@Test
	public void testBoardTile() {
		Board b = new Board();
		BoardTile[] bts = b.getTiles();
		BoardTile bt = bts[210];
		assertEquals(true, b.isBoardEmpty());
		assertEquals("TW", bt.getTileType());
		assertEquals(210, bt.getTileNumber());
	}

	/**
	 * Checks if players are initialized with a score of 0
	 */
	@Test
	public void testPlayerScore() {
		GamePlay gp = new GamePlay();
		Player p1 = new Player(1);
		assertEquals(0, gp.getScorePlayerOne());
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