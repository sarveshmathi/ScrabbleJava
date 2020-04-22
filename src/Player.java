import java.util.ArrayList;

import javafx.event.EventHandler;
/**
 * This class deals with the players.
 * 
 * @author Team 51
 *
 */
import javafx.scene.Parent;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class Player extends Parent {
	private int number; // player 0 or 1
	// private String name; // player name
	public int score;
	public ArrayList<LetterTilePic> rackletters = new ArrayList<LetterTilePic>();

	/**
	 * This constructs an object of the type Player.
	 * 
	 * @param number - the player's number
	 * @param name   - the player's name
	 */
	public Player(int number) {
		this.number = number;
		this.score = 0;
	}

	public void turn() {
		ArrayList<LetterTilePic> currentletters;
	}

	public void genrack(LetterBag lb) {
		DataFormat LetterTilePic = null;
		rackletters = lb.getLetters(7);
		for (LetterTilePic letter : rackletters) {
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
