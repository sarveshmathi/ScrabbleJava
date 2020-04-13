import java.util.ArrayList;

/**
 * Carries out scoring for the game
 * @author Team 51
 *
 */

public class Scoring {
	
	/**
	 * This method takes an ArrayList of words and returns the total score if the words are acceptable
	 * in the format "Accepted,score" or if there is an invalid word it returns a string in the 
	 * format Rejected (Incorrect word: word,-1"
	 * @param inputArray
	 * @return
	 */

	public static String checkInput(ArrayList<String> inputArray) {
		System.out.println("Checking words . . . ");
		int totalScore = 0;
		for (String word : inputArray) {
			if (!OnlineDictionary.checkWord(word)) {
				System.out.println("Check complete");
				return ("Rejected (Incorrect word: " + word + "),-1");
			} else {
				totalScore += wordPoints(word);
			}
		}
		System.out.println("Check complete");
		return "Accepted," + totalScore;
	}
	
/**
 * Calculates the total points for an accepted word and returns an integer
 * @param word
 * @return
 */
	private static int wordPoints(String word) {
		int wordPoints = 0;
		for (int i = 0; i < word.length(); i++) {
			String letter = Character.toString(word.charAt(i)).toUpperCase();
			int letterPoints = LetterTilePic.Letters.valueOf(letter).points;
			wordPoints += letterPoints;
		}
		return wordPoints;
	}
	
//	public static void main(String[] args) {
//		ArrayList<String> words = new ArrayList<String>();
//		words.add("hello");
//		words.add("bye");
//		words.add("z");
//		System.out.println(Scoring.checkInput(words));
//		
//	}
	
}
