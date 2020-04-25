import java.util.ArrayList;

import javafx.scene.Parent;

/**
 * This class creates an instance of each player.
 * 
 * @author Team 51
 *
 */

public class Player extends Parent {
	private int number; // player 0 or 1
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
