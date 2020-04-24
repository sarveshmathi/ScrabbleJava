
/**
 * This class carries out scoring for the game.
 *
 * @author Team 51
 *
 */

public class Scoring {
	int TWCounter = 0;// number of TW
	int DWCounter = 0;// number of DW
	boolean mouseOn = false;// checks if the word is made by at least 1 new piece
	boolean mouseOff = false; // at least one letter has to be on board already
	int counterCall = 0;

	/**
	 * This method receives the word input by the player and determines if it is a
	 * valid dictionary word and fixes the final score for the player.
	 * 
	 * @param inputArray - the word played by the player
	 * @param board      - the current board
	 * @param player     - the current player
	 * @return finalPoints - if valid word: the actual final score if invalid word :
	 *         error codes (-1 Incorrect Word -2 Words Mismatch -888 Current Tile
	 *         Not Used)
	 */
	public int scoreFinal(String word, Board board, Player player) {
		System.out.println("Checking words . . . ");
		int finalPoints = 0;

		int tempTotalScore = scoreFaceValue(word, board, player);
		if (tempTotalScore == 0) {
			AlertBox.alertWithoutUserAction("Words Mismatch",
					"The word that you typed is different from the word you played.");

			finalPoints = -2;
		}

		else if (tempTotalScore == -888) {
			AlertBox.alertWithoutUserAction("Current Tile Not Used",
					"A new word made must use at least a tile from existing board.");

			finalPoints = -888;

		}

		else {
			if (!OnlineDictionary.checkWord(word)) {


				player.score -= tempTotalScore;
				finalPoints = -1;
			}

			else {
				finalPoints = tempTotalScore;
				board.turnOffDrag();
				counterCall++;
			}
		}


		System.out.println("Checking words complete");
		return finalPoints;
	}

	/**
	 * This method calculates the score of a word by considering all the factors
	 * (points of a tile and placement on the board) except the dictionary validity
	 * of the word.
	 * 
	 * @param word   - the word played by the player
	 * @param board  - the current board
	 * @param player - the current player
	 * @return turnPoints - the points scored by the player in current turn before
	 *         considering dictionary validity
	 * 
	 */

	public int scoreFaceValue(String word, Board board, Player player) {
		boolean firstTurn = false;
		mouseOn = false;// reset
		mouseOff = false;// reset
		int turnPoints = 0;
		boolean present = false;

		if (board.isBoardEmpty()) {
			firstTurn = true;
		}

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
								turnPoints = isWordPresent(board2d, word, row, col, 0, 1);
							} else if (isWordPresent(board2d, word, row, col, 0, -1) != 0) {
								turnPoints = isWordPresent(board2d, word, row, col, 0, -1);
							}
							present = true;
							break outer;
						}

						// SEARCH VERTICAL both ways
						// if you found the word
						else if (isWordPresent(board2d, word, row, col, 1, 0) != 0
								|| isWordPresent(board2d, word, row, col, -1, 0) != 0) {

							if (isWordPresent(board2d, word, row, col, 1, 0) != 0) {
								turnPoints = isWordPresent(board2d, word, row, col, 1, 0);
							} else if (isWordPresent(board2d, word, row, col, -1, 0) != 0) {
								turnPoints = isWordPresent(board2d, word, row, col, -1, 0);
							}
							present = true;
							break outer;
						}

					}
				}
			}
		}
		// this checks if it was made by at least 1 new letter
		//System.out.println(mouseOff);
		if (mouseOn == true && mouseOff == true && firstTurn == false) {
			// takes into account triple of double word scores
			turnPoints = (int) (turnPoints * (Math.pow(3, TWCounter)) * (Math.pow(2, DWCounter)));
			player.score += turnPoints;
		} else if (mouseOn == true && mouseOff == false && counterCall == 0) {
			turnPoints = (int) (turnPoints * (Math.pow(3, TWCounter)) * (Math.pow(2, DWCounter)));
			player.score += turnPoints;
		} else if (mouseOn == true && mouseOff == false && counterCall != 0) {
			player.score += 0;
			turnPoints = -888;// false value used for detecting that they didn;t use tile on board
			// alert user must use tile on board
		} else {
			player.score += 0;
		}
		//System.out.println("Turnpoints: " + turnPoints);
		//System.out.println("points before anything: " + player.score);
		return turnPoints;
	}

	/**
	 * This method increases points for each tile based on their particular location
	 * on the board.
	 * 
	 * @param boardTile - the board tile
	 * @return points - the points for each tile
	 */
	public int pointCall(BoardTile boardTile) {
		// check if it is a new letter by seeing the mouse
		if (boardTile.holds.isMouseTransparent() != true) {
			mouseOn = true;
		}
		if (boardTile.holds.isMouseTransparent() == true) {
			mouseOff = true;
		}
		int points = 0;
		if (boardTile.getTileType().equals("TL")) {
			points = 3 * (boardTile.holds.letter.points);
		} else if (boardTile.getTileType().equals("DL")) {
			points = 2 * (boardTile.holds.letter.points);
		} else if (boardTile.getTileType().equals("TW")) {
			points = boardTile.holds.letter.points;
			incrementWordSpecial(boardTile);
		} else if (boardTile.getTileType().equals("DW")) {
			incrementWordSpecial(boardTile);
			points = boardTile.holds.letter.points;
		} else if (boardTile.getTileType().equals("C")) {
			points = 2 * (boardTile.holds.letter.points);
		} else {
			points = boardTile.holds.letter.points;
		}
		return points;
	}

	/**
	 * This method increases the counter of TWCounter (Triple Words) or DWCounter
	 * (Double Words) based on where the tile is.
	 * 
	 * @param boardTile - the board tile
	 */
	public void incrementWordSpecial(BoardTile boardTile) {
		if (boardTile.getTileType().equals("TW")) {
			TWCounter++;
		} else if (boardTile.getTileType().equals("DW")) {
			DWCounter++;
		}
	}

	/**
	 * This inner method helps to find words in horizontal(left to right and right
	 * to left) and vertical (left to right and right to left) and tallys the number
	 * of Triple and double words
	 * 
	 * @param board2D      - the two dimensional 15x15 board
	 * @param word         - the word played by the player
	 * @param row          -row of where the 1st letter of the word you are looking
	 *                     for was found
	 * @param column       -column of where the 1st letter of the word you are
	 *                     looking for was found
	 * @param rowIncrement - used to determine which row direction to go in and by
	 *                     how much to increase our position on the board by that
	 *                     amount, for example 1 for right and -1 for left
	 * @param colIncrement -used to determine which column direction to go in and by
	 *                     how much to increase our position on the board by that
	 *                     amount, for example 1 for right and -1 for left
	 * @return turnPoints -how many points the player gets in the turn taking into
	 *         account DL, TL but NOT DW or TW
	 */
	public int isWordPresent(BoardTile[][] board2D, String word, int row, int column, int rowIncrement,
			int columnIncrement) {
		TWCounter = 0;// reset counters for how many triple words
		DWCounter = 0;// reset counters
		int turnPoints = 0;// points for this player's turn
		int searchColumn = column;// starting column
		int searchRow = row;// starting row
		for (int charIndex = 0; charIndex < word.length(); charIndex++) {
			// checks if it would be out of bounds or if they aren't equal
			if (searchRow < 0 || searchColumn < 0 || searchRow >= board2D.length
					|| searchColumn >= board2D[searchRow].length || board2D[searchRow][searchColumn].holds == null
					|| !board2D[searchRow][searchColumn].holds.letter.toString()
					.equals(Character.toString(word.charAt(charIndex)).toUpperCase())) {
				// if you got here it is because you did NOT find the word
				TWCounter = 0;// reset counters
				DWCounter = 0;// reset counters
				mouseOn = false;// reset
				mouseOff = false; // reset
				turnPoints = 0; // erase any points that may have been added
				return turnPoints;
			} else {
				turnPoints += pointCall(board2D[searchRow][searchColumn]);// get the points
				searchColumn += columnIncrement;
				searchRow += rowIncrement;
			}
		}
		// if you got here you found the word, return the number of points
		return turnPoints;
	}

}
