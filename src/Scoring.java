import java.util.ArrayList;

/**
 * Carries out scoring for the game
 *
 * @author Team 51
 *
 */

public class Scoring {
	int TWcounter = 0;// number of TW
	int DWcounter = 0;// number of DW
	boolean mouseon = false;// checks if the word is made by at least 1 new piece

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
	/** fix player points based on accepted word) */
	public int checkInputboard(ArrayList<String> inputArray, Board board, Player player) {
		System.out.println("Checking words . . . ");
		int totalScore = 0;
		for (String word : inputArray) {

			int tempTotalScore = wordptb(word, board, player);
			if (tempTotalScore == 0) {
				AlertBox.alertWithoutUserAction("Words Mismatch",
						"The word that you typed is different from the word you played.");
				// if (response) {
				totalScore = -2;
				// } else {
				// totalScore = -2;
				// }
			} else {
				if (!OnlineDictionary.checkWord(word)) {
					System.out.println("Checking words complete");
					AlertBox.alertWithoutUserAction("Incorrect Word",
							word + " is not a valid word in Scrabble Dictionary.");
					player.score -= player.score;
					totalScore = -1;
				}

				else {
					totalScore = tempTotalScore;
					board.turnoffdrag();
				}
			}

			// }
		}
		System.out.println("Checking words complete");
		return totalScore;
	}

	public int wordptb(String word, Board board, Player player) {
		// TWcounter = 0;// reset
		// DWcounter = 0;// reset
		mouseon = false;// reset
		int turnpoints = 0;
		boolean present = false;
		BoardTile[][] board2d = new BoardTile[15][15];
		int x = 0;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				board2d[i][j] = board.getTiles()[x];
				x++;
			}
		}
		outer: for (int row = 0; row < board2d.length; row++) {
			for (int col = 0; col < board2d[row].length; col++) {
				if (board2d[row][col].holds != null) {
					String tile_letter = board2d[row][col].holds.letter.toString();
					String word_letter_0 = Character.toString(word.charAt(0)).toUpperCase();
					if (tile_letter.equals(word_letter_0)) {
						// we found the 1st character
						// SEARCH HORIZONTAL LEFT RIGHT
						// this is going to look horizontally
						// if you found the word
						if (isWordPresent(board2d, word, row, col, 0, 1) != 0
								|| isWordPresent(board2d, word, row, col, 0, -1) != 0) {
							// see which is the non zero one
							if (isWordPresent(board2d, word, row, col, 0, 1) != 0) {
								turnpoints = isWordPresent(board2d, word, row, col, 0, 1);
							} else if (isWordPresent(board2d, word, row, col, 0, -1) != 0) {
								turnpoints = isWordPresent(board2d, word, row, col, 0, -1);
							}
							present = true;
							break outer;
						}

						// SEARCH VERTICAL both ways
						// if you found the word
						else if (isWordPresent(board2d, word, row, col, 1, 0) != 0
								|| isWordPresent(board2d, word, row, col, -1, 0) != 0) {

							if (isWordPresent(board2d, word, row, col, 1, 0) != 0) {
								turnpoints = isWordPresent(board2d, word, row, col, 1, 0);
							} else if (isWordPresent(board2d, word, row, col, -1, 0) != 0) {
								turnpoints = isWordPresent(board2d, word, row, col, -1, 0);
							}
							present = true;
							break outer;
						}

					}
				}
			}
		}
		// this checks if it was made by at least 1 new letter
		if (mouseon == true) {
			// takes into account triple of double word scores
			player.score += (turnpoints * (Math.pow(3, TWcounter)) * (Math.pow(2, DWcounter)));
		} else {
			player.score += 0;
		}
		return turnpoints;
	}

	/** gets points per letter */
	public int pointcal(BoardTile bt) {
		// check if it is a new letter by seeing the mouse
		if (bt.isMouseTransparent() != true) {
			mouseon = true;
		}
		int points = 0;
		if (bt.getTileType().equals("TL")) {
			points = 3 * (bt.holds.letter.points);
		} else if (bt.getTileType().equals("DL")) {
			points = 2 * (bt.holds.letter.points);
		} else if (bt.getTileType().equals("TW")) {
			points = bt.holds.letter.points;
			incrementwordspecial(bt);
		} else if (bt.getTileType().equals("DW")) {
			incrementwordspecial(bt);
			points = bt.holds.letter.points;
		} else if (bt.getTileType().equals("C")) {
			points = 2 * (bt.holds.letter.points);
		} else {
			points = bt.holds.letter.points;
		}
		return points;
	}

	/** this makes the tw or dw switch turn on if off if on */
	public void incrementwordspecial(BoardTile bt) {
		if (bt.getTileType().equals("TW")) {
			TWcounter++;
		} else if (bt.getTileType().equals("DW")) {
			DWcounter++;
		}
	}

	/** inner method to help find words in any direction(repeated method) */
	public int isWordPresent(BoardTile[][] board2d, String word, int row, int col, int rowIncrement, int colIncrement) {
		TWcounter = 0;// reset counters
		DWcounter = 0;// reset counters
		int turnPoints = 0;
		int searchcol = col;
		int searchrow = row;
		boolean allcharfound = true;
		for (int charIndex = 0; charIndex < word.length(); charIndex++) {
			// checks if it would be out of bounds or if they aren't equal
			if (searchrow < 0 || searchcol < 0 || searchrow >= board2d.length || searchcol >= board2d[searchrow].length
					|| board2d[searchrow][searchcol].holds == null || !board2d[searchrow][searchcol].holds.letter
							.toString().equals(Character.toString(word.charAt(charIndex)).toUpperCase())) {
				allcharfound = false;
				TWcounter = 0;// reset counters
				DWcounter = 0;// reset counters
				turnPoints = 0; // erase any points that may have been added
				return turnPoints;
			} else {
				turnPoints += pointcal(board2d[searchrow][searchcol]);// get the points
				searchcol += colIncrement;
				searchrow += rowIncrement;
			}
		}
		return turnPoints;
	}

}
