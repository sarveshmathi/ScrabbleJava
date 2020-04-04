import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.Parent;

/**
 * Represents a 15 x 15 board
 * 
 * @author Team 51
 *
 */
public class Board extends Parent {

	private BoardTile[] tiles;

	public Board() {
		tiles = new BoardTile[225];
		int tile_in_row = 15;
		int tile_in_col = 15;
		setTileNumbersAndValues();
		for (int i = 0; i < tiles.length; i++) {
			BoardTile bt = tiles[i];
			bt.setTranslateX(40 * (i % tile_in_row));
			bt.setTranslateY(40 * (i / tile_in_row));
			getChildren().add(bt);
		}
	}

	public BoardTile[] getTiles() {
		return tiles;
	}

	/**
	 * Sets tile numbers from 0 to 224 and values none(N), double letter (DL),
	 * double word (DW), triple letter (TL), triple word (TW), center tile (C)
	 */

	public void setTileNumbersAndValues() {
		ArrayList<Integer> tripleWordTiles = new ArrayList<>(Arrays.asList(0, 7, 14, 105, 119, 210, 217, 224));
		ArrayList<Integer> tripleLetterTiles = new ArrayList<>(
				Arrays.asList(20, 24, 76, 80, 84, 88, 136, 140, 144, 148, 200, 204));
		ArrayList<Integer> doubleWordTiles = new ArrayList<>(
				Arrays.asList(16, 28, 32, 42, 48, 56, 64, 70, 154, 160, 168, 176, 182, 208));
		ArrayList<Integer> doubleLetterTiles = new ArrayList<>(Arrays.asList(3, 11, 36, 38, 45, 52, 59, 92, 96, 98, 102,
				108, 116, 122, 126, 128, 132, 165, 172, 179, 186, 188, 213, 221));
		for (int i = 0; i < 225; i++) {
			if (tripleWordTiles.contains(i)) {
				tiles[i] = new BoardTile(i, "TW");
			} else if (tripleLetterTiles.contains(i)) {
				tiles[i] = new BoardTile(i, "TL");
			} else if (doubleWordTiles.contains(i)) {
				tiles[i] = new BoardTile(i, "DW");
			} else if (doubleLetterTiles.contains(i)) {
				tiles[i] = new BoardTile(i, "DL");
			} else if (i == 112) {
				tiles[i] = new BoardTile(i, "C");
			} else {
				tiles[i] = new BoardTile(i, "N");
			}
		}
	}

	/**
	 * This method prints board on the console.
	 */
	public void printBoard() {
		for (int i = 0; i < tiles.length; i++) {
			BoardTile bt = tiles[i];
			if (i % 15 == 0) {
				System.out.println();
			}
			if (!bt.getTileType().equals("N")) {
				System.out.format("%7s", tiles[i].getTileType());
			} else {
				System.out.format("%7s", tiles[i].getTileNumber());
			}

		}
	}

	public static void main(String[] args) {
		Board b = new Board();
		b.printBoard();
	}
}
