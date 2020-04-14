import java.util.ArrayList;

/**
 * Carries out scoring for the game
 * 
 * @author Team 51
 *
 */

public class Scoring {
	/**
	 * This method takes an ArrayList of words and returns the total score if the
	 * words are acceptable or -1
	 * 
	 * @param inputArray
	 * @return returns total score or -1 
	 */

	public static int checkInput(ArrayList<String> inputArray) {
		System.out.println("Checking words . . . ");
		int totalScore = 0;
		for (String word : inputArray) {
			if (!OnlineDictionary.checkWord(word)) {
				System.out.println("Checking words complete");
				return -1;
			} else {
				totalScore += wordPoints(word);
			}
		}
		System.out.println("Checking words  complete");
		return totalScore;
	}

	/**
	 * Calculates the total points for an accepted word
	 * 
	 * @param word
	 * @return total points for a word
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
