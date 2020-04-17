
import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 * Represents a 15 x 15 board
 * 
 * @author Team 51
 *
 */
public class Board extends Parent {
	/**
	 * 
	 */
	private BoardTile[] tiles;
	private BoardTile tile;
	DataFormat LetterTilePic = new DataFormat("hello");

	public Board() {
		newboard();
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
				Arrays.asList(16, 28, 32, 42, 48, 56, 64, 70, 154, 160, 168, 176, 182, 192, 196, 208));
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
//	public void printBoard() {
//		for (int i = 0; i < tiles.length; i++) {
//			BoardTile bt = tiles[i];
//			if (i % 15 == 0) {
//				System.out.println();
//			}
//			if (!bt.getTileType().equals("N")) {
//				System.out.format("%7s", tiles[i].getTileType());
//			} else {
//				System.out.format("%7s", tiles[i].getTileNumber());
//			}
//
//		}
//	}

	/**
	 * this method only updates the board after each move (we should update scoring
	 * here too maybe
	 * 
	 */
	public void boardgen() {
		// tiles = new BoardTile[225];
		int tile_in_row = 15;
		int tile_in_col = 15;
		// setTileNumbersAndValues();
		for (int i = 0; i < tiles.length; i++) {
			BoardTile bt = tiles[i];
			if (bt.holds != null) {
				bt.displayHold();
			}

		}
	}

	public BoardTile getTile() {
		return tile;
	}

	public void turnoffdrag() {
		for (BoardTile t : tiles) {
			if (t.holds != null) {
				t.setMouseTransparent(true);
			}
		}
	}

	/**
	 * this method creates a new board display instance
	 */
	public void newboard() {
		tiles = new BoardTile[225];
		int tile_in_row = 15;
		int tile_in_col = 15;
		setTileNumbersAndValues();
		for (int i = 0; i < tiles.length; i++) {
			BoardTile bt = tiles[i];
			bt.setTranslateX(40 * (i % tile_in_row));
			bt.setTranslateY(40 * (i / tile_in_row));
			getChildren().add(bt);
			bt.setOnDragOver(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					System.out.println("test");
					/* data is dragged over the target */
					/*
					 * accept it only if it is not dragged from the same node and if it isn't
					 * holding a card
					 */
					if (!isboardempty()) {
						if (event.getGestureSource() != bt && bt.getholds() == null) {
							/* allow for both copying and moving, whatever user chooses */
							event.acceptTransferModes(TransferMode.MOVE);
							bt.displayHold();
						}
					} else if (isboardempty()) {
						if (bt.getTileType().equals("C")) {
							event.acceptTransferModes(TransferMode.MOVE);
							bt.displayHold();
						}
					}
					event.consume();
				}
			});
			bt.setOnDragDropped(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					/* data dropped */
					/* if there is a string data on dragboard, read it and use it */
					Dragboard db = event.getDragboard();
					Object j = db.getContent(LetterTilePic);
					bt.holds = (LetterTilePic) event.getGestureSource();
					bt.holds.isin = bt;
					for (BoardTile t : tiles) {
						if (t.holds == bt.holds && bt != t) {
							t.holds = null;
						}
					}
					// bt.holds = (LetterTilePic) event.getAcceptingObject();
					boolean success = false;
					if (bt.getholds() != null) {
						success = true;
						getChildren().add(bt.holds);
						bt.displayHold();
						// boardgen();
						// bt.setMouseTransparent(true);
					}
					event.setDropCompleted(success);
					event.consume();
				}
			});
			if (bt.holds != null) {
				bt.holds.setOnDragDetected(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						/* drag was detected, start a drag-and-drop gesture */
						/* allow any transfer mode */
						Dragboard db = bt.holds.startDragAndDrop(TransferMode.MOVE);
						/* Put a string on a dragboard */
						ClipboardContent content = new ClipboardContent();
						// content.putString(letter.toString());
						content.put(LetterTilePic, bt.holds);
						db.setContent(content);
						System.out.println("I'm busy");
						bt.holds = null;
						event.consume();
					}
				});

				bt.setOnDragDone(new EventHandler<DragEvent>() {
					public void handle(DragEvent event) {
						/* the drag and drop gesture ended */
						/* if the data was successfully moved, clear it */
						if (event.getTransferMode() == TransferMode.MOVE) {
							bt.holds = null;
						}
						event.consume();
					}
				});

			}
			/*
			 * let the source know whether the string was successfully transferred and used
			 */

		}
	}

	/** checks to see if the board is empty */
	public boolean isboardempty() {
		boolean val = true;
		for (BoardTile bt : tiles) {
			if (bt.holds != null) {
				val = false;
			}
		}
		return val;
	}

	public BoardTile[][] turnto2d() {
		BoardTile[][] board2d = new BoardTile[15][15];
		int x = 0;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				board2d[i][j] = tiles[x];
				x++;
			}
		}
		return board2d;
	}

//checks if the tiles are together 
	public boolean checktogether() {
		boolean boo = true;
		int i = 0; // index
		ArrayList<Integer> tilelist = new ArrayList<Integer>();
		for (BoardTile bt : tiles) {
			if (!bt.isMouseTransparent() && bt.getholds() != null) {
				tilelist.add(bt.getTileNumber());
				System.out.println(bt.getTileNumber());
			}
		}
		int[] tileno = new int[tilelist.size()];
		for (Integer j : tilelist) {
			tileno[i] = j;
			i++;
		}
		for (int k = 0; k < tileno.length; k++) {
			if (k < tileno.length - 1) {
				int dif = Math.abs((tileno[k + 1]) - tileno[k]);
				if (dif % 15 != 0) {
					if (dif != 1) {
						// if you are at leftmost
						if (i == 0) {
							if (tiles[i + 1].holds == null) {
								boo = false;
								break;
							}
						}
						// if you are at rightmost
						else if (i == (tiles.length - 1)) {
							if (tiles[i - 1].holds == null) {
								boo = false;
								break;
							}

						} else {
							if (tiles[i + 1].holds == null && tiles[i - 1].holds == null) {
								boo = false;
								break;
							}
						}

					} else if (dif == 1) {
						boo = true;
					}

				}

			}
		}
		return boo;
	}
}
