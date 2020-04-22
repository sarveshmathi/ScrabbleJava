import java.util.ArrayList;

import javafx.event.EventHandler;

import javafx.scene.Parent;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 * This class deals with the two individual players - their scores and playing racks.
 * 
 * @author Team 51
 *
 */

public class Player extends Parent {
	private int number; // player 0 or 1
	// private String name; // player name
	public int score;
	public ArrayList<LetterTilePicture> rackletters = new ArrayList<LetterTilePicture>();

	/**
	 * This constructs an object of the type Player.
	 * 
	 * @param number - the player's number
	 */
	public Player(int number) {
		this.number = number;
		this.score = 0;
	}
	
//	/**
//	 * 
//	 */
//	public void turn() {
//		ArrayList<LetterTilePic> currentletters;
//	}
	
	/**
	 * This method generates rack for the current player.
	 * @param lb - the current letter bag
	 */
	public void genrack(LetterBag lb) {
		DataFormat LetterTilePic = null;
		rackletters = lb.getLetters(7);
		for (LetterTilePicture letter : rackletters) {
			getChildren().add(letter);
			// letter.onMouseDragEnteredProperty();
			letter.setOnDragDetected(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					/* drag was detected, start a drag-and-drop gesture */
					/* allow any transfer mode */
					Dragboard db = letter.startDragAndDrop(TransferMode.MOVE);
					/* Put a string on a dragboard */
					ClipboardContent content = new ClipboardContent();
					// content.putString(letter.toString());
					content.put(LetterTilePic, letter);
					// content.getFiles();
					// currentTile.getClass();
					db.setContent(content);
					event.consume();
				}
			});

			letter.setOnDragDone(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					/* the drag and drop gesture ended */
					/* if the data was successfully moved, clear it */
					if (event.getTransferMode() == TransferMode.MOVE) {
						getChildren().remove(letter);
					}
					event.consume();
				}
			});
		}
	}

}
