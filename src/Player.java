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
 * This class creates an instance of each player.
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
}
